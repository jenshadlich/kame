package de.jeha.kame.crawler.scheduler.services;

import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import de.jeha.kame.crawler.service.api.CrawlRequest;
import de.jeha.kame.crawler.service.api.CrawlResponse;
import de.jeha.kame.crawler.service.client.CrawlerServiceClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
public class QuartzCrawlJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzCrawlJob.class);

    public static final String CRAWL_JOB = "CRAWL_JOB";
    public static final String CRAWLER_SERVICE_CLIENT = "CRAWLER_SERVICE_CLIENT";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CrawlJob crawlJob = getCrawlJob(context);
        CrawlerServiceClient client = getCrawlerServiceClient(context);

        LOG.info("{}", crawlJob);

        try {
            CrawlResponse response = client.crawl(new CrawlRequest(crawlJob.getSeedUrl()));
            LOG.info("{}", response.getId());
        } catch (IOException e) {
            throw new JobExecutionException(e);
        }

    }

    private CrawlJob getCrawlJob(JobExecutionContext context) {
        return (CrawlJob) context.getJobDetail().getJobDataMap().get(CRAWL_JOB);
    }

    private CrawlerServiceClient getCrawlerServiceClient(JobExecutionContext context) {
        return (CrawlerServiceClient) context.getJobDetail().getJobDataMap().get(CRAWLER_SERVICE_CLIENT);
    }

}
