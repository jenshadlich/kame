package de.jeha.kame.crawler.scheduler.services;

import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class DefaultSchedulerService implements SchedulerService, SchedulerLifecycle {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultSchedulerService.class);

    private final Scheduler scheduler;

    public DefaultSchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void addJob(CrawlJob crawlJob) throws SchedulerException {
        /*
        JobDetail jobDetail = new JobDetailImpl();

        try {
            scheduler.addJob(jobDetail, false);
        } catch (org.quartz.SchedulerException e) {
            throw new SchedulerException(e);
        }
        */
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
