package de.jeha.kame.crawler.service.config;

/**
 * @author jenshadlich@googlemail.com
 */
public class MongoDbConfiguration {

    private final String url;
    private final String database;

    public MongoDbConfiguration(String url, String database) {
        this.url = url;
        this.database = database;
    }

    public String getUrl() {
        return url;
    }

    public String getDatabase() {
        return database;
    }

}
