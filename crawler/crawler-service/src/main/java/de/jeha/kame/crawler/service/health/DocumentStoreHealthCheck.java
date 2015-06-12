package de.jeha.kame.crawler.service.health;

import com.codahale.metrics.health.HealthCheck;
import de.jeha.kame.crawler.service.ds.DocumentStore;

/**
 * @author jenshadlich@googlemail.com
 */
public class DocumentStoreHealthCheck extends HealthCheck {

    private final DocumentStore documentStore;

    public DocumentStoreHealthCheck(DocumentStore documentStore) {
        this.documentStore = documentStore;
    }

    @Override
    protected Result check() throws Exception {
        return documentStore.checkHealth();
    }

}