package de.jeha.kame.crawler.service.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.stereotype.Service;

/**
 * @author jenshadlich@googlemail.com
 */
@Service("mongoRepository")
public class MongoRepository {

    final MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost"));

    public MongoClient getClient() {
        return client;
    }

}
