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

    @NotNull
    @Valid
    private DocumentStoreFactory documentStore;

    @JsonCreator
    public CrawlerServiceConfiguration(@JsonProperty("mongoDb") MongoDbFactory mongoDb,
                                       @JsonProperty("crawler") CrawlerFactory crawler,
                                       @JsonProperty("documentStore") DocumentStoreFactory documentStoreFactory) {
        this.mongoDb = mongoDb;
        this.crawler = crawler;
        this.documentStore = documentStoreFactory;
    }

    @JsonProperty("mongoDb")
    public MongoDbFactory getMongoDb() {
        return mongoDb;
    }

    @JsonProperty("crawler")
    public CrawlerFactory getCrawler() {
        return crawler;
    }

    @JsonProperty("documentStore")
    public DocumentStoreFactory getDocumentStore() {
        return documentStore;
    }

}
