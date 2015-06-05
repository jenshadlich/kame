package de.jeha.kame.crawler.service.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.CommandResult;
import de.jeha.kame.crawler.service.config.MongoDbFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class MongoDbHealthCheck extends HealthCheck {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDbHealthCheck.class);

    private final MongoDbFactory mongoDb;

    public MongoDbHealthCheck(MongoDbFactory mongoDb) {
        this.mongoDb = mongoDb;
    }

    @Override
    protected Result check() throws Exception {
        CommandResult result = mongoDb.buildClient().getDB(mongoDb.getDatabase()).getStats();

        for (String key : result.keySet()) {
            LOG.debug("{}: {}", key, result.get(key));
        }

        final Double okValue = (Double) result.get("ok");
        return okValue == 1.0
                ? HealthCheck.Result.healthy()
                : HealthCheck.Result.unhealthy("");
    }

}