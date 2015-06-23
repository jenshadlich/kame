package de.jeha.kame.crawler.scheduler.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerServiceFactory {

    @NotEmpty
    @JsonProperty
    private String url;

    public CrawlerServiceConfiguration build() {
        return new CrawlerServiceConfiguration(url);
    }

}
