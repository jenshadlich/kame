package de.jeha.kame.crawler.service.health;

import com.codahale.metrics.health.HealthCheck;
import org.springframework.stereotype.Service;

/**
 * @author jenshadlich@googlemail.com
 */
@Service
public class DatabaseHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        // TODO: implement check
        return HealthCheck.Result.healthy();
    }

}