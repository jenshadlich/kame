package de.jeha.kame.crawler.scheduler;


import de.jeha.kame.crawler.scheduler.config.CrawlerSchedulerConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.ServerProperties;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerScheduler extends Application<CrawlerSchedulerConfiguration> {

    private static final String APPLICATION_NAME = "crawler-scheduler";

    public static void main(String... args) throws Exception {
        new CrawlerScheduler().run(args);
    }

    @Override
    public String getName() {
        return APPLICATION_NAME;
    }

    @Override
    public void initialize(Bootstrap<CrawlerSchedulerConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CrawlerSchedulerConfiguration configuration, Environment environment) {
        environment.jersey().disable(ServerProperties.WADL_FEATURE_DISABLE);
    }

}
