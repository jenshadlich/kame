package de.jeha.kame.crawler.service.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlRequest {

    @JsonProperty
    private final String url;

    public CrawlRequest(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
