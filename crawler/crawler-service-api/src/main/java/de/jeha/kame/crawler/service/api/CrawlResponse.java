package de.jeha.kame.crawler.service.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrawlResponse {

    @JsonProperty
    private final String id;

    @JsonProperty
    private final String date;

    @JsonProperty
    private final Integer statusCode;

    @JsonProperty
    private final Error error;

    @JsonProperty
    private final List<String> links;

    @JsonCreator
    private CrawlResponse(@JsonProperty("id") String id,
                          @JsonProperty("date") String date,
                          @JsonProperty("statusCode") Integer statusCode,
                          @JsonProperty("links") List<String> links,
                          @JsonProperty("error") Error error) {
        this.id = id;
        this.date = date;
        this.statusCode = statusCode;
        this.links = links;
        this.error = error;
    }

    private CrawlResponse(String id, String date, Error error) {
        this(id, date, null, null, error);
    }

    private CrawlResponse(String id, String date, int statusCode, List<String> links) {
        this(id, date, statusCode, links, null);
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public Error getError() {
        return error;
    }

    public List<String> getLinks() {
        return links;
    }

    public boolean hasError() {
        return error != null;
    }

    public static CrawlResponse withSuccess(String id, String date, int statusCode, List<String> links) {
        return new CrawlResponse(id, date, statusCode, links);
    }

    public static CrawlResponse withError(String id, String date, String errorMessage) {
        return new CrawlResponse(id, date, new Error(errorMessage));
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static class Error {

        @JsonProperty
        private final String errorMessage;

        @JsonCreator
        public Error(@JsonProperty("errorMessage") String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

    }
}
