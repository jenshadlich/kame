package de.jeha.kame.crawler.core.types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 */
public class Headers {
    private final Map<String, String> headers = new HashMap<>();

    public Headers(Map<String, String> headers) {
        this.headers.putAll(headers);
    }

    public void put(String key, String value) {
        headers.put(key, value);
    }

    public String getContentType() {
        return headers.get("Content-Type");
    }
}
