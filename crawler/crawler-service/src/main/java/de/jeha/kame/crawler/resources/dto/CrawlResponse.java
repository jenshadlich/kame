package de.jeha.kame.crawler.resources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlResponse {

    private final String id;
    private final int statusCode;

    public CrawlResponse(String id, int statusCode) {
        this.id = id;
        this.statusCode = statusCode;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public int getStatusCode() {
        return statusCode;
    }
}
