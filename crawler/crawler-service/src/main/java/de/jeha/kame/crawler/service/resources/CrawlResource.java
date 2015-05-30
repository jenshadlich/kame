package de.jeha.kame.crawler.service.resources;


import com.codahale.metrics.annotation.Timed;
import de.jeha.kame.crawler.Crawler;
import de.jeha.kame.crawler.service.resources.dto.CrawlRequest;
import de.jeha.kame.crawler.service.resources.dto.CrawlResponse;
import de.jeha.kame.crawler.types.CrawlResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.UUID;

@Path("/")
@Service
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