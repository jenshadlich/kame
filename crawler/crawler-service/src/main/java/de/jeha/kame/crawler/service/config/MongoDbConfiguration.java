package de.jeha.kame.crawler.service.config;

/**
 * @author jenshadlich@googlemail.com
 */
public class MongoDbConfiguration {

    private final String url;

    public MongoDbConfiguration(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
