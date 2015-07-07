package de.jeha.kame.crawler.scheduler.services;

import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import de.jeha.kame.crawler.service.api.CrawlRequest;
import de.jeha.kame.crawler.service.api.CrawlResponse;
import de.jeha.kame.crawler.service.client.CrawlerServiceClient;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

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

        LOG.info("Start job '{}'.", crawlJob.getId());
        LOG.info("Details: {}", crawlJob);

        CrawlStats crawlStats = new CrawlStats();

        Deque<String> urls = new ArrayDeque<>();
        urls.push(crawlJob.getSeedUrl());

        for (int i = 0; i < crawlJob.getLimit(); i++) {
            crawlStats.attempts++;

            if (urls.isEmpty()) {
                break;
            }

            final String urlToCrawl = urls.pop();

            try {
                CrawlResponse response = client.crawl(new CrawlRequest(urlToCrawl));
                LOG.debug("{}", response.getId());

                if (response.hasError()) {
                    crawlStats.errors++;
                }

                for (String url : response.getLinks()) {
                    urls.push(url);
                    crawlStats.urlsCollected++;
                }

            } catch (IOException e) {
                LOG.warn("failed to crawl '{}'", urlToCrawl);
                crawlStats.errors++;
            }
        }

        LOG.info("Finished job {}.", crawlJob.getId());
        LOG.info("Crawl stats: {}", crawlStats);
    }

    private CrawlJob getCrawlJob(JobExecutionContext context) {
        return (CrawlJob) context.getJobDetail().getJobDataMap().get(CRAWL_JOB);
    }

    private CrawlerServiceClient getCrawlerServiceClient(JobExecutionContext context) {
        return (CrawlerServiceClient) context.getJobDetail().getJobDataMap().get(CRAWLER_SERVICE_CLIENT);
    }

    static class CrawlStats {

        int attempts = 0;
        int errors = 0;
        int urlsCollected = 0;

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
        }

    }

}
