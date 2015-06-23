package de.jeha.kame.crawler.scheduler.resources;


import com.codahale.metrics.annotation.Timed;
import de.jeha.kame.crawler.scheduler.api.SubmitCrawlJobRequest;
import de.jeha.kame.crawler.scheduler.api.SubmitCrawlJobResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/")
public class SubmitCrawlJobResource {

    private static final Logger LOG = LoggerFactory.getLogger(SubmitCrawlJobResource.class);

    @POST
    @Path("/submitCrawlJob")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public SubmitCrawlJobResponse submitCrawlJob(SubmitCrawlJobRequest request) {
        LOG.info("{}", request);

        return new SubmitCrawlJobResponse(UUID.randomUUID().toString());
    }

}