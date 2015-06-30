package de.jeha.kame.crawler.scheduler.services;

/**
 * @author jenshadlich@googlemail.com
 */
public interface SchedulerLifecycle {

    public void start() throws SchedulerException;

    public void suspend() throws SchedulerException;

}
