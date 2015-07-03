package de.jeha.kame.crawler.scheduler.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.jeha.kame.crawler.service.client.CrawlerServiceClient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerServiceFactory {

    @NotEmpty
    @JsonProperty
    private String endpointUrl;

    public CrawlerServiceConfiguration build() {
        return new CrawlerServiceConfiguration(endpointUrl);
    }

    public CrawlerServiceClient buildClient() {
        return new CrawlerServiceClient(endpointUrl);
    }

}
