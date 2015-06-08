package de.jeha.kame.crawler.service.resources;


import com.codahale.metrics.annotation.Timed;
import de.jeha.kame.crawler.core.Crawler;
import de.jeha.kame.crawler.core.LinkExtractor;
import de.jeha.kame.crawler.core.robots.RobotsMetaContent;
import de.jeha.kame.crawler.core.robots.RobotsMetaContentExtractor;
import de.jeha.kame.crawler.core.types.CrawlResult;
import de.jeha.kame.crawler.service.api.CrawlRequest;
import de.jeha.kame.crawler.service.api.CrawlResponse;
import de.jeha.kame.crawler.service.config.CrawlerConfiguration;
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

@Path("/")
public class CrawlResource {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlResult.class);

    private final CrawlerConfiguration crawlerConfig;
    private final LinkExtractor linkExtractor = new LinkExtractor();
    private final RobotsMetaContentExtractor robotsMetaContentExtractor = new RobotsMetaContentExtractor();

    public CrawlResource(CrawlerConfiguration crawlerConfig) {
        this.crawlerConfig = crawlerConfig;
    }

    @POST
    @Path("/crawl")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public CrawlResponse crawl(CrawlRequest request) {
        LOG.info("{}", request.getUrl());
        final String crawlId = UUID.randomUUID().toString();
        final String now = ZonedDateTime.now(ZoneOffset.UTC).toString();

        Crawler crawler = new Crawler(request.getUrl(), crawlerConfig.getUserAgent());
        try {
            CrawlResult result = crawler.execute();

            List<String> links = linkExtractor.get(result);
            for (String link : links) {
                LOG.debug("{}", link);
            }
            RobotsMetaContent robotsMetaContent = robotsMetaContentExtractor.get(result);
            LOG.debug("{}", robotsMetaContent);

            return CrawlResponse.withSuccess(
                    crawlId,
                    now,
                    result.getMetadata().getStatusCode()
            );
        } catch (IOException e) {
            LOG.warn("An I/O error occurred", e);

            return CrawlResponse.withError(crawlId, now, e.getMessage());
        }
    }

}