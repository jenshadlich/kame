package de.jeha.kame.crawler.cli.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class UrlDAO {

    private static final Logger LOG = LoggerFactory.getLogger(UrlDAO.class);

    private final DBCollection urlsCollection;

    public UrlDAO(final DB crawlerDatabase) {
        urlsCollection = crawlerDatabase.getCollection("urls");
    }

    // validates that username is unique and insert into db
    public boolean addUrl(String url) {
        BasicDBObject urlObj = new BasicDBObject();

        urlObj.append("_id", url);

        try {
            urlsCollection.insert(urlObj);
            return true;
        } catch (DuplicateKeyException e) {
            LOG.warn("Duplicate key '{}'", url);
            return false;
        }
    }

}
