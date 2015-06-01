package de.jeha.kame.crawler.service.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * @author jenshadlich@googlemail.com
 */
public class MongoDbHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        // TODO: implement check
        return HealthCheck.Result.healthy();
    }

}