package de.jeha.kame.crawler.scheduler.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jenshadlich@googlemail.com
 */
public class SubmitCrawlJobResponse {

    private final String id;

    public SubmitCrawlJobResponse(String id) {
        this.id = id;
    }

    @JsonProperty
    public String getId() {
        return id;
    }
}
