package de.jeha.kame.crawler.service;

import de.jeha.kame.crawler.service.config.CrawlerServiceConfiguration;
import de.jeha.kame.crawler.service.health.MongoDbHealthCheck;
import de.jeha.kame.crawler.service.resources.CrawlResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.ServerProperties;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerService extends Application<CrawlerServiceConfiguration> {

    private static final String APPLICATION_NAME = "crawler-service";

    public static void main(String... args) throws Exception {
        new CrawlerService().run(args);
    }

    @Override
    public String getName() {
        return APPLICATION_NAME;
    }

    @Override
    public void initialize(Bootstrap<CrawlerServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CrawlerServiceConfiguration configuration, Environment environment) {
        environment.jersey().register(
                new CrawlResource(
                        configuration.getCrawler().getUserAgent(),
                        configuration.getDocumentStore().build()));

        environment.healthChecks().register("mongoDb", new MongoDbHealthCheck(configuration.getMongoDb()));

        environment.jersey().disable(ServerProperties.WADL_FEATURE_DISABLE);
    }

}
