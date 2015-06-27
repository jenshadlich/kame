package de.jeha.kame.crawler.core.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 */
public class Headers {

    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String DATE = "Date";
    public static final String ETAG = "ETag";
    public static final String EXPIRES = "Expires";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String RETRY_AFTER = "Retry-After";
    public static final String PRAGMA = "Pragma";
    public static final String SERVER = "Server";
    public static final String X_ROBOTS_TAG = "X-Robots-Tag";
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";

    private final Map<String, String> headers = new HashMap<>();

    public Headers() {
    }

    public Headers(Map<String, String> headers) {
        this.headers.putAll(headers);
    }

    public void put(String key, String value) {
        headers.put(key, value);
    }

    /**
     * @param key
     * @return
     * @todo Replace String parameter with enum
     */
    public String get(String key) {
        return headers.get(key);
    }

    public String getContentType() {
        return headers.get(CONTENT_TYPE);
    }

}
