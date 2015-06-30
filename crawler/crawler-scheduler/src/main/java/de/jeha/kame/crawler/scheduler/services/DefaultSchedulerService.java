package de.jeha.kame.crawler.scheduler.services;

import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;

/**
 * @author jenshadlich@googlemail.com
 */
public class DefaultSchedulerService implements SchedulerService, SchedulerLifecycle {

    private final Scheduler scheduler;

    public DefaultSchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void addJob(CrawlJob crawlJob) throws SchedulerException {
        JobKey jobKey = new JobKey(crawlJob.getId());
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(QuartzCrawlJob.class)
                .withIdentity(jobKey)
                .usingJobData(QuartzCrawlJob.NAME, crawlJob.getName())
                .usingJobData(QuartzCrawlJob.SEED_URL, crawlJob.getSeedUrl())
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
