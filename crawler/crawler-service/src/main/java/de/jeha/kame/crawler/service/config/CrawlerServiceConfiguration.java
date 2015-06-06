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

    @Valid
    @NotNull
    private CrawlerFactory crawler = new CrawlerFactory();

    @JsonCreator
    public CrawlerServiceConfiguration(@JsonProperty("mongoDb") MongoDbFactory mongoDb,
                                       @JsonProperty("crawler") CrawlerFactory crawler) {
        this.mongoDb = mongoDb;
        this.crawler = crawler;
    }

    @JsonProperty("mongoDb")
    public MongoDbFactory getMongoDb() {
        return this.mongoDb;
    }

    @JsonProperty("crawler")
    public CrawlerFactory getCrawler() {
        return this.crawler;
    }

}
