package de.jeha.kame.crawler.scheduler.resources;


import com.codahale.metrics.annotation.Timed;
import de.jeha.kame.crawler.scheduler.api.SubmitCrawlJobRequest;
import de.jeha.kame.crawler.scheduler.api.SubmitCrawlJobResponse;
import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import de.jeha.kame.crawler.scheduler.services.SchedulerException;
import de.jeha.kame.crawler.scheduler.services.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class CrawlJobResource {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlJobResource.class);

    private final SchedulerService schedulerService;

    public CrawlJobResource(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @POST
    @Path("/submitCrawlJob")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public SubmitCrawlJobResponse submitCrawlJob(SubmitCrawlJobRequest request) {
        LOG.info("{}", request);

        CrawlJob crawlJob = new CrawlJob(
                request.getName(),
                request.getSeedUrl(),
                request.getAllowedDomains()
        );

        try {
            schedulerService.addJob(crawlJob);
        } catch (SchedulerException e) {
            LOG.error("Unable to add job", e);
            return new SubmitCrawlJobResponse(null); // TODO: add an appropriate error response
        }

        return new SubmitCrawlJobResponse(crawlJob.getId());
    }

}