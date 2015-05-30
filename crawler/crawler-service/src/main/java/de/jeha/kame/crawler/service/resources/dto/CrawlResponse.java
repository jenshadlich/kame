package de.jeha.kame.crawler.service.resources.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jenshadlich@googlemail.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrawlResponse {

    private final String id;
    private int statusCode;
    private final Error error;

    private CrawlResponse(String id, int statusCode) {
        this.id = id;
        this.statusCode = statusCode;
        this.error = null;
    }

    private CrawlResponse(String id, Error error) {
        this.id = id;
        this.error = error;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public int getStatusCode() {
        return statusCode;
    }

    @JsonProperty()
    public Boolean hasError() {
        return error == null ? null : Boolean.TRUE;
    }

    @JsonProperty
    public Error getError() {
        return error;
    }

    public static CrawlResponse withSuccess(String id, int statusCode) {
        return new CrawlResponse(id, statusCode);
    }

    public static CrawlResponse withError(String id, String errorMessage) {
        return new CrawlResponse(id, new Error(errorMessage));
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static class Error {

        private final String errorMessage;

        public Error(String errorMessage) {
            this.errorMessage = errorMessage;
        }

    }
}
