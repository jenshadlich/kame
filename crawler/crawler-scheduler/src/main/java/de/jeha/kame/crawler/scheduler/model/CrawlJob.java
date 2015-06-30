package de.jeha.kame.crawler.scheduler.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.UUID;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlJob {

    private final String id;
    private final String name;
    private final String seedUrl;
    private final List<String> allowedDomains;

    public CrawlJob(String name, String seedUrl, List<String> allowedDomains) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.seedUrl = seedUrl;
        this.allowedDomains = allowedDomains;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    public List<String> getAllowedDomains() {
        return allowedDomains;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
