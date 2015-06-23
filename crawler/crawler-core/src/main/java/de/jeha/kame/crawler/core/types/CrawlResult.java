package de.jeha.kame.crawler.core.types;

import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 * @todo replace by Page
 */
public class CrawlResult {

    private final String url;
    private final String content;
    private final Metadata metadata;

    public CrawlResult(String url, String content, Metadata metadata) {
        this.url = url;
        this.content = content;
        this.metadata = metadata;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public boolean hasContent() {
        return content != null && !content.isEmpty();
    }

    public String getContentType() {
        return getMetadata().getHeaders().getContentType();
    }

    public Metadata getMetadata() {
        return metadata;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static class Metadata {

        private final Headers headers;
        private final int statusCode;
        private final long timeTaken;

        public Metadata(Map<String, String> headers, int statusCode, long timeTaken) {
            this.headers = new Headers(headers);
            this.statusCode = statusCode;
            this.timeTaken = timeTaken;
        }

        public Metadata(Headers headers, int statusCode, long timeTaken) {
            this.headers = headers;
            this.statusCode = statusCode;
            this.timeTaken = timeTaken;
        }

        public Headers getHeaders() {
            return headers;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public long getTimeTaken() {
            return timeTaken;
        }
    }

}