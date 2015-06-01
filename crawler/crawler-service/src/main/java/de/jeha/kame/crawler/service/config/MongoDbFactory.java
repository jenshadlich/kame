package de.jeha.kame.crawler.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.MongoClient;

/**
 * @author jenshadlich@googlemail.com
 */
public class MongoDbFactory {

    private String url;

    @JsonProperty
    public String getUrl() {
        return url;
    }

    @JsonProperty
    public void setUrl(String url) {
        this.url = url;
    }

    public MongoDbConfiguration build() {
        return new MongoDbConfiguration(url);
    }

    public MongoClient buildClient() {
        return new MongoClient(url);
    }

}
