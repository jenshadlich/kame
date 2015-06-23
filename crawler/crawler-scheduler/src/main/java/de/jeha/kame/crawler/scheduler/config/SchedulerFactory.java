package de.jeha.kame.crawler.scheduler.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

/**
 * @author jenshadlich@googlemail.com
 */
public class SchedulerFactory {

    @NotEmpty
    @JsonProperty
    private String jobStore;

    @NotEmpty
    @JsonProperty
    private String threadPool;

    @NotEmpty
    @JsonProperty
    private String threadCount;

    private transient Scheduler scheduler;

    public String getJobStore() {
        return jobStore;
    }

    public String getThreadPool() {
        return threadPool;
    }

    public String getThreadCount() {
        return threadCount;
    }

    public SchedulerConfiguration build() {
        return new SchedulerConfiguration(jobStore, threadPool, threadCount);
    }

    public Scheduler buildScheduler() throws SchedulerException {
        if (scheduler == null) {
            Properties properties = new Properties();
            properties.setProperty("org.quartz.jobStore.class", jobStore);
            properties.setProperty("org.quartz.threadPool.class", threadPool);
            properties.setProperty("org.quartz.threadPool.threadCount", threadCount);

            scheduler = new StdSchedulerFactory(properties).getScheduler();
        }

        return scheduler;
    }
}
