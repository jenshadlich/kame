package de.jeha.kame.crawler.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.MongoClient;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerFactory {

    @JsonProperty
    private String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    public CrawlerConfiguration build() {
        return new CrawlerConfiguration(userAgent);
    }

}
