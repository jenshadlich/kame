package de.jeha.kame.crawler.resources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlResponse {

    private final String id;

    public CrawlResponse(String id) {
        this.id = id;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

}
