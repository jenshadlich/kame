package de.jeha.kame.crawler.scheduler.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author jenshadlich@googlemail.com
 */
public class SubmitCrawlJobRequest {

    @JsonProperty
    private String name;

    @JsonProperty
    private String seedUrl;
    //private List<String> allowedDomains;

    public String getName() {
        return name;
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
