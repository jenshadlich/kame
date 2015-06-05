package de.jeha.kame.crawler.service;

import de.jeha.kame.crawler.service.config.CrawlerServiceConfiguration;
import de.jeha.kame.crawler.service.health.MongoDbHealthCheck;
import de.jeha.kame.crawler.service.resources.CrawlResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerService extends Application<CrawlerServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new CrawlerService().run(args);
    }

    @Override
    public String getName() {
        return "crawler-service";
    }

    @Override
    public void initialize(Bootstrap<CrawlerServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CrawlerServiceConfiguration configuration, Environment environment) {
        environment.jersey().register(new CrawlResource());

        environment.healthChecks().register("mongoDb", new MongoDbHealthCheck(configuration.getMongoDb()));

        // TODO: move to config
        environment.jersey().disable("com.sun.jersey.config.feature.DisableWADL");
        environment.jersey().enable("com.sun.jersey.config.feature.CanonicalizeURIPath");
    }

}
