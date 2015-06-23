package de.jeha.kame.crawler.scheduler.quartz;

import io.dropwizard.lifecycle.Managed;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author jenshadlich@googlemail.com
 */
public class ManagedScheduler implements Managed {

    private static final Logger LOG = LoggerFactory.getLogger(ManagedScheduler.class);

    private Scheduler scheduler;

    @Override
    public void start() throws Exception {
        LOG.info("start Quartz scheduler");

        // TODO: move to config
        Properties properties = new Properties();
        properties.setProperty("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        properties.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        properties.setProperty("org.quartz.threadPool.threadCount", "4");

        scheduler = new StdSchedulerFactory(properties).getScheduler();
        scheduler.start();
    }

    @Override
    public void stop() throws Exception {
        LOG.info("stop Quartz scheduler");
        scheduler.shutdown(true);
    }

}
