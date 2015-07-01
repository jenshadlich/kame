package de.jeha.kame.crawler.core.http;

/**
 * @author jenshadlich@googlemail.com
 */
public enum ContentType {

    APPLICATION_JSON("application/json"),
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain");

    private final String value;

    ContentType(String contentType) {
        this.value = contentType;
    }

    public String getValue() {
        return value;
    }

}
