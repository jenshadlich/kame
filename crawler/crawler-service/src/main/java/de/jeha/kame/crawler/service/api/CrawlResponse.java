package de.jeha.kame.crawler.service.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jenshadlich@googlemail.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrawlResponse {

    private final String id;
    private final String date;
    private int statusCode;
    private final Error error;

    private CrawlResponse(String id, String date, int statusCode) {
        this.id = id;
        this.date = date;
        this.statusCode = statusCode;
        this.error = null;
    }

    private CrawlResponse(String id, String date, Error error) {
        this.id = id;
        this.date = date;
        this.error = error;
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

    public static CrawlResponse withSuccess(String id, String date, int statusCode) {
        return new CrawlResponse(id, date, statusCode);
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

    }
}
