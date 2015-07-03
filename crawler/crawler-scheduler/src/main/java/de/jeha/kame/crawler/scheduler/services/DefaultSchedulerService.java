package de.jeha.kame.crawler.scheduler.services;

import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import de.jeha.kame.crawler.service.client.CrawlerServiceClient;
import org.quartz.*;

/**
 * @author jenshadlich@googlemail.com
 */
public class DefaultSchedulerService implements SchedulerService, SchedulerLifecycle {

    private final Scheduler scheduler;
    private final CrawlerServiceClient crawlerServiceClient;

    public DefaultSchedulerService(Scheduler scheduler, CrawlerServiceClient crawlerServiceClient) {
        this.scheduler = scheduler;
        this.crawlerServiceClient = crawlerServiceClient;
    }

    @Override
    public void addJob(CrawlJob crawlJob) throws SchedulerException {
        JobKey jobKey = new JobKey(crawlJob.getId());

        JobDataMap jobData = new JobDataMap();
        jobData.put(QuartzCrawlJob.CRAWL_JOB, crawlJob);
        jobData.put(QuartzCrawlJob.CRAWLER_SERVICE_CLIENT, crawlerServiceClient);

        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(QuartzCrawlJob.class)
                .withIdentity(jobKey)
                .setJobData(jobData)
                .storeDurably(false)
                .requestRecovery(false)
                .build();

        try {
            scheduler.addJob(jobDetail, false, true);
            scheduler.triggerJob(jobKey);
        } catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e);
        }
    }

    @Override
    public void start() throws SchedulerException {
        try {
            scheduler.start();
        } catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e);
        }
    }

    @Override
    public void suspend() throws SchedulerException {
        try {
            scheduler.standby();
        } catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e);
        }
    }

}
