package de.jeha.kame.crawler.scheduler.config;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerServiceConfiguration {

    private final String url;

    public CrawlerServiceConfiguration(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
