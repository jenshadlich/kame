package de.jeha.kame.crawler.service.resources;


import com.codahale.metrics.annotation.Timed;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import de.jeha.kame.crawler.core.Crawler;
import de.jeha.kame.crawler.core.LinkExtractor;
import de.jeha.kame.crawler.core.robots.RobotsMetaContent;
import de.jeha.kame.crawler.core.robots.RobotsMetaContentExtractor;
import de.jeha.kame.crawler.core.types.CrawlResult;
import de.jeha.kame.crawler.service.api.CrawlRequest;
import de.jeha.kame.crawler.service.api.CrawlResponse;
import de.jeha.kame.crawler.service.ds.DocumentStore;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Path("/")
public class CrawlResource {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlResult.class);
    private static final String[] DEFAULT_SCHEMES = new String[]{"http", "https"};

    private final String userAgent;
    private final DocumentStore documentStore;
    private final ConnectionFactory connectionFactory;

    private final UrlValidator urlValidator = new UrlValidator(DEFAULT_SCHEMES);
    private final LinkExtractor linkExtractor = new LinkExtractor();
    private final RobotsMetaContentExtractor robotsMetaContentExtractor = new RobotsMetaContentExtractor();

    public CrawlResource(String userAgent, DocumentStore documentStore, ConnectionFactory connectionFactory) {
        this.userAgent = userAgent;
        this.documentStore = documentStore;
        this.connectionFactory = connectionFactory;
    }

    @POST
    @Path("/crawl")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public CrawlResponse crawl(CrawlRequest request) {
        LOG.info("Crawl: '{}'", request.getUrl());

        final String crawlId = UUID.randomUUID().toString();
        final ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

        if (!urlValidator.isValid(request.getUrl())) {
            return CrawlResponse.withError(crawlId, now.toString(), "invalid url");
        }

        Crawler crawler = new Crawler(request.getUrl(), userAgent);
        try {
            CrawlResult result = crawler.execute();

            documentStore.save(crawlId, now, result);

            publishCrawlResult(crawlId);

            List<String> links = linkExtractor.get(result);
            for (String link : links) {
                LOG.debug("{}", link);
            }
            RobotsMetaContent robotsMetaContent = robotsMetaContentExtractor.get(result);
            LOG.debug("{}", robotsMetaContent);

            return CrawlResponse.withSuccess(
                    crawlId,
                    now.toString(),
                    result.getMetadata().getStatusCode()
            );
        } catch (IOException e) {
            LOG.warn("An I/O error occurred", e);

            return CrawlResponse.withError(crawlId, now.toString(), e.getMessage());
        }
    }

    private void publishCrawlResult(String crawlId) {

        byte[] bytes = crawlId.getBytes(); // TODO: real message body

        AMQP.BasicProperties.Builder message = new AMQP.BasicProperties.Builder();
        message.messageId(UUID.randomUUID().toString());
        message.correlationId(crawlId);

        try {
            Channel channel = null;
            Connection connection = null;
            try {
                connection = connectionFactory.newConnection();
                channel = connection.createChannel();
                channel.queueDeclare("new_url", false, false, false, null);
                channel.basicPublish("", "new_url", false, false, message.build(), bytes);
            } finally {
                if (channel != null) {
                    channel.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (TimeoutException | IOException e) {
            LOG.warn("Unable to publish crawl result", e);
        }
        LOG.debug("published crawl result");
    }

}