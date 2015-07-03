package de.jeha.kame.crawler.scheduler.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jeha.kame.crawler.core.http.ContentType;
import de.jeha.kame.crawler.core.http.Headers;
import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import de.jeha.kame.crawler.service.api.CrawlRequest;
import de.jeha.kame.crawler.service.api.CrawlResponse;
import de.jeha.kame.crawler.service.client.CrawlerServiceClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

    private final CrawlerServiceClient crawlerServiceClient = new CrawlerServiceClient("http://localhost:8080/crawl"); // TODO: get from config

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CrawlJob crawlJob = (CrawlJob) context.getJobDetail().getJobDataMap().get(CRAWL_JOB);
        LOG.info("{}", crawlJob);

        try {
            CrawlResponse response = crawlerServiceClient.crawl(new CrawlRequest(crawlJob.getSeedUrl()));
            LOG.info("{}", response.getId());
        } catch (IOException e) {
            throw new JobExecutionException(e);
        }

    }

}
