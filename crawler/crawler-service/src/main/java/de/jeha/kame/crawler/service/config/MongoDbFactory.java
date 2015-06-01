package de.jeha.kame.crawler.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.MongoClient;

/**
 * @author jenshadlich@googlemail.com
 */
public class MongoDbFactory {

    @JsonProperty
    private String url;

    @JsonProperty
    private String database;

    public String getUrl() {
        return url;
    }

    public String getDatabase() {
        return database;
    }

    public MongoDbConfiguration build() {
        return new MongoDbConfiguration(url, database);
    }

    public MongoClient buildClient() {
        return new MongoClient(url);
    }

}
