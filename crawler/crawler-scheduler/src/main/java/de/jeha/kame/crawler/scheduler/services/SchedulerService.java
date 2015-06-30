package de.jeha.kame.crawler.scheduler.services;

import de.jeha.kame.crawler.scheduler.model.CrawlJob;

/**
 * @author jenshadlich@googlemail.com
 */
public interface SchedulerService {

    public void addJob(CrawlJob crawlJob);

}
