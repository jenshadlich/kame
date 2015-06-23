package de.jeha.kame.crawler.scheduler.config;

/**
 * @author jenshadlich@googlemail.com
 */
public class SchedulerConfiguration {

    private final String instanceId;
    private final String jobStore;
    private final String threadPool;
    private final String threadCount;

    public SchedulerConfiguration(String instanceId, String jobStore, String threadPool, String threadCount) {
        this.instanceId = instanceId;
        this.jobStore = jobStore;
        this.threadPool = threadPool;
        this.threadCount = threadCount;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getJobStore() {
        return jobStore;
    }

    public String getThreadPool() {
        return threadPool;
    }

    public String getThreadCount() {
        return threadCount;
    }

}
