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
    private CrawlerServiceFactory crawlerService;

    @Valid
    @NotNull
    private SchedulerFactory scheduler;

    public CrawlerSchedulerConfiguration(@JsonProperty("crawler-service") CrawlerServiceFactory crawlerService,
                                         @JsonProperty("scheduler") SchedulerFactory scheduler) {
        this.crawlerService = crawlerService;
        this.scheduler = scheduler;
    }

    public CrawlerServiceFactory getCrawlerService() {
        return crawlerService;
    }

    public SchedulerFactory getScheduler() {
        return scheduler;
    }
}
