package de.jeha.kame.crawler.service.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerServiceConfiguration extends Configuration {

    @Valid
    @NotNull
    private MongoDbFactory mongoDb = new MongoDbFactory();

    @JsonCreator
    public CrawlerServiceConfiguration(@JsonProperty("mongoDb") MongoDbFactory mongoDb) {
        this.mongoDb = mongoDb;
    }

    @JsonProperty("mongoDb")
    public MongoDbFactory getMongoDb() {
        return this.mongoDb;
    }
}
