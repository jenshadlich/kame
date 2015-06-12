package de.jeha.kame.crawler.service.resources;


import com.codahale.metrics.annotation.Timed;
import de.jeha.kame.crawler.core.Crawler;
import de.jeha.kame.crawler.core.LinkExtractor;
import de.jeha.kame.crawler.core.robots.RobotsMetaContent;
import de.jeha.kame.crawler.core.robots.RobotsMetaContentExtractor;
import de.jeha.kame.crawler.core.types.CrawlResult;
import de.jeha.kame.crawler.service.api.CrawlRequest;
import de.jeha.kame.crawler.service.api.CrawlResponse;
import de.jeha.kame.crawler.service.ds.DocumentStore;
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

    private final String userAgent;
    private final DocumentStore documentStore;

    private final LinkExtractor linkExtractor = new LinkExtractor();
    private final RobotsMetaContentExtractor robotsMetaContentExtractor = new RobotsMetaContentExtractor();

    public CrawlResource(String userAgent, DocumentStore documentStore) {
        this.userAgent = userAgent;
        this.documentStore = documentStore;
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

        Crawler crawler = new Crawler(request.getUrl(), userAgent);
        try {
            CrawlResult result = crawler.execute();

            documentStore.save(crawlId, now, result);

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

}