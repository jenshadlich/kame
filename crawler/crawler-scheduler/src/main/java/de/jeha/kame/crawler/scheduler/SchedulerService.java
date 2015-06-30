package de.jeha.kame.crawler.scheduler;

import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import org.quartz.Scheduler;

/**
 * @author jenshadlich@googlemail.com
 */
public class SchedulerService {

    private final Scheduler scheduler;

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void addJob(CrawlJob crawlJob) {
    }

}
