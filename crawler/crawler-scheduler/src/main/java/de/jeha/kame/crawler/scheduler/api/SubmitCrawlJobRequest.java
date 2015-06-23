package de.jeha.kame.crawler.scheduler.api;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author jenshadlich@googlemail.com
 */
public class SubmitCrawlJobRequest {

    private String name;
    private String seedUrl;
    //private List<String> allowedDomains;

    public String getName() {
        return name;
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeedUrl(String seedUrl) {
        this.seedUrl = seedUrl;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
