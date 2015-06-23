package de.jeha.kame.crawler.service.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlRequest {

    @JsonProperty
    private String url;

    public String getUrl() {
        return url;
    }

}
