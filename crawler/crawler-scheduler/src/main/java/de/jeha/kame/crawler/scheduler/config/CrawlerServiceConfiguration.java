package de.jeha.kame.crawler.scheduler.config;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerServiceConfiguration {

    private final String endpointUrl;

    public CrawlerServiceConfiguration(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

}
