package de.jeha.kame.crawler.scheduler.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerSchedulerConfiguration extends Configuration {

    @Valid
    @NotNull
    CrawlerServiceFactory crawlerService;

    public CrawlerSchedulerConfiguration(@JsonProperty("crawler-service") CrawlerServiceFactory crawlerService) {
        this.crawlerService = crawlerService;
    }

    public CrawlerServiceFactory getCrawlerService() {
        return crawlerService;
    }

}
