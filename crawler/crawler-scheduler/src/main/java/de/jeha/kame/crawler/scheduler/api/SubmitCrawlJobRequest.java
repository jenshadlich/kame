package de.jeha.kame.crawler.scheduler.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class SubmitCrawlJobRequest {

    @JsonProperty
    private String name;

    @JsonProperty
    private String seedUrl;

    @JsonProperty
    private List<String> allowedDomains;

    @JsonProperty
    private int limit;

    public String getName() {
        return name;
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    public List<String> getAllowedDomains() {
        return allowedDomains;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
