package de.jeha.kame.crawler.service.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.jeha.kame.crawler.service.ds.DocumentStore;
import io.dropwizard.jackson.Discoverable;

/**
 * @author jenshadlich@googlemail.com
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface DocumentStoreFactory extends Discoverable {

    DocumentStore build();

}
