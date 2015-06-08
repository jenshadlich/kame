package de.jeha.kame.crawler.core.types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 */
public class Headers {

    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String PRAGMA = "Pragma";
    public static final String SERVER = "Server";
    public static final String X_ROBOTS_TAG = "X-Robots-Tag";

    private final Map<String, String> headers = new HashMap<>();

    public Headers(Map<String, String> headers) {
        this.headers.putAll(headers);
    }

    public void put(String key, String value) {
        headers.put(key, value);
    }

    public String get(String key) {
        return headers.get(key);
    }

    public String getContentType() {
        return headers.get(CONTENT_TYPE);
    }

}
