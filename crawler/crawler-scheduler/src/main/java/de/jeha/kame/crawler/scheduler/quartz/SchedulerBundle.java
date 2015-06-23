package de.jeha.kame.crawler.scheduler.quartz;

import com.codahale.metrics.SharedMetricRegistries;
import de.jeha.kame.crawler.scheduler.config.CrawlerSchedulerConfiguration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author jenshadlich@googlemail.com
 */
public class SchedulerBundle implements ConfiguredBundle<CrawlerSchedulerConfiguration> {

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        SharedMetricRegistries.add("scheduler", bootstrap.getMetricRegistry());
    }

    @Override
    public void run(CrawlerSchedulerConfiguration configuration, Environment environment) throws Exception {
        environment.lifecycle().manage(new ManagedScheduler());
    }
    
}
