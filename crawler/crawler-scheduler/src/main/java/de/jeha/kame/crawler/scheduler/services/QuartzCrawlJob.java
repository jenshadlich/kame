package de.jeha.kame.crawler.scheduler.services;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class QuartzCrawlJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzCrawlJob.class);

    public static final String NAME = "NAME";
    public static final String SEED_URL = "SEED_URL";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("{}", context.getJobDetail().getJobDataMap().get(NAME));
        LOG.info("{}", context.getJobDetail().getJobDataMap().get(SEED_URL));
    }

}
