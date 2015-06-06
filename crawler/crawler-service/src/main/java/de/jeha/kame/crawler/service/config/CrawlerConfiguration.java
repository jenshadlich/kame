package de.jeha.kame.crawler.service.config;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerConfiguration {

    private String userAgent;

    public CrawlerConfiguration(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
    }

}
