package de.jeha.kame.crawler.scheduler.services;

import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
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
    public void addJob(CrawlJob crawlJob) {
    }

    @Override
    public void start() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            LOG.error("Unable to start the scheduler", e);
        }
    }

    @Override
    public void suspend() {
        try {
            scheduler.standby();
        } catch (SchedulerException e) {
            LOG.error("Unable to suspend the scheduler", e);
        }
    }

}
