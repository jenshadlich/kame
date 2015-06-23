package de.jeha.kame.crawler.scheduler;


import de.jeha.kame.crawler.scheduler.config.CrawlerSchedulerConfiguration;
import de.jeha.kame.crawler.scheduler.config.CrawlerServiceConfiguration;
import de.jeha.kame.crawler.scheduler.quartz.SchedulerBundle;
import de.jeha.kame.crawler.scheduler.resources.CrawlJobResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerScheduler extends Application<CrawlerSchedulerConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlerScheduler.class);
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
        bootstrap.addBundle(new SchedulerBundle());
    }

    @Override
    public void run(CrawlerSchedulerConfiguration configuration, Environment environment) {
        CrawlerServiceConfiguration crawlerServiceConfiguration = configuration.getCrawlerService().build();
        LOG.info("crawler-service.url: {}", crawlerServiceConfiguration.getUrl());

        environment.jersey().register(new CrawlJobResource());

        environment.jersey().disable(ServerProperties.WADL_FEATURE_DISABLE);
    }

}
