package de.jeha.kame.crawler.scheduler.services;

import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import org.quartz.Scheduler;

/**
 * @author jenshadlich@googlemail.com
 */
public class DefaultSchedulerService implements SchedulerService {

    private final Scheduler scheduler;

    public DefaultSchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void addJob(CrawlJob crawlJob) {
    }

}
