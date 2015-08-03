package de.jeha.kame.crawler.common.http;

/**
 * @author jenshadlich@googlemail.com
 */
public enum StatusCode {

    // 1xx
    SC_100(100, "Continue"),
    // 2xx
    SC_200(200, "OK"),
    SC_201(201, "Accepted"),
    // 3xx
    SC_301(301, "Moved Permanently"),
    SC_302(302, "Found"),
    SC_304(304, "Not Modified"),
    // 4xx
    SC_400(400, "Bad Request"),
    SC_401(401, "Unauthorized"),
    SC_403(403, "Forbidden"),
    SC_429(429, "Too Many Requests"),
    // 5xx
    SC_500(500, "Internal Server Error"),
    SC_501(501, "Not Implemented"),
    SC_502(502, "Bad Gateway"),
    SC_503(503, "Service Unavailable"),
    SC_504(504, "Gateway Timeout");

    private final int code;
    private final String phrase;

    StatusCode(int code, String phrase) {
        this.code = code;
        this.phrase = phrase;
    }

    public int getCode() {
        return code;
    }

    public String getPhrase() {
        return phrase;
    }

    public boolean is1XX() {
        return isCodeInClass(100);
    }
    
    public boolean is2XX() {
        return isCodeInClass(200);
    }

    public boolean is3XX() {
        return isCodeInClass(300);
    }

    public boolean is4XX() {
        return isCodeInClass(400);
    }

    public boolean is5XX() {
        return isCodeInClass(500);
    }

    private boolean isCodeInClass(int lowerBound) {
        return code >= lowerBound && code <= lowerBound + 99;
    }
}
