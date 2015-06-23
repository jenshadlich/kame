package de.jeha.kame.crawler.scheduler.quartz;

import io.dropwizard.lifecycle.Managed;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class ManagedScheduler implements Managed {

    private static final Logger LOG = LoggerFactory.getLogger(ManagedScheduler.class);

    private Scheduler scheduler;

    public ManagedScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void start() throws Exception {
        LOG.info("start Quartz scheduler");
        scheduler.start();
    }

    @Override
    public void stop() throws Exception {
        LOG.info("stop Quartz scheduler");
        scheduler.shutdown(true);
    }

}
