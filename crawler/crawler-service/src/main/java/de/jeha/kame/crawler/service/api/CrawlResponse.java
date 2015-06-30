package de.jeha.kame.crawler.service.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrawlResponse {

    private final String id;
    private final String date;
    private final Integer statusCode;
    private final Error error;
    private final List<String> links;

    private CrawlResponse(String id, String date, int statusCode, List<String> links) {
        this.id = id;
        this.date = date;
        this.statusCode = statusCode;
        this.links = links;
        this.error = null;
    }

    private CrawlResponse(String id, String date, Error error) {
        this.id = id;
        this.date = date;
        this.error = error;
        this.statusCode = null;
        this.links = null;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getDate() {
        return date;
    }

    @JsonProperty
    public Integer getStatusCode() {
        return statusCode;
    }

    @JsonProperty
    public List<String> getLinks() {
        return links;
    }

    @JsonProperty
    public Boolean hasError() {
        return error == null ? null : Boolean.TRUE;
    }

    @JsonProperty
    public Error getError() {
        return error;
    }

    public static CrawlResponse withSuccess(String id, String date, int statusCode, List<String> links) {
        return new CrawlResponse(id, date, statusCode, links);
    }

    public static CrawlResponse withError(String id, String date, String errorMessage) {
        return new CrawlResponse(id, date, new Error(errorMessage));
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static class Error {

        private final String errorMessage;

        public Error(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @JsonProperty
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
