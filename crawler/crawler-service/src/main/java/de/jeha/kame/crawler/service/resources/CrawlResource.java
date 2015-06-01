package de.jeha.kame.crawler.service.resources;


import com.codahale.metrics.annotation.Timed;
import de.jeha.kame.crawler.core.Crawler;
import de.jeha.kame.crawler.core.types.CrawlResult;
import de.jeha.kame.crawler.service.api.CrawlRequest;
import de.jeha.kame.crawler.service.api.CrawlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.UUID;

@Path("/")
public class CrawlResource {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlResult.class);

    @POST
    @Path("/crawl")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Object crawl(CrawlRequest request) {
        LOG.info("{}", request.getUrl());
        final String crawlId = UUID.randomUUID().toString();

        Crawler crawler = new Crawler(request.getUrl());
        try {
            CrawlResult result = crawler.execute();

            return CrawlResponse.withSuccess(
                    crawlId,
                    result.getMetadata().getStatusCode()
            );
        } catch (IOException e) {
            LOG.warn("An I/O error occurred", e);

            return CrawlResponse.withError(crawlId, e.getMessage());
        }
    }

}