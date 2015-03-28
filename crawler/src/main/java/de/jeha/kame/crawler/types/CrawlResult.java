package de.jeha.kame.crawler.types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlResult {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlResult.class);

    private final String url;
    private final String content;
    private final String contentType;
    private final int statusCode;
    private final long timeTaken;

    public CrawlResult(String url, String content, String contentType, int statusCode, long timeTaken) {
        this.url = url;
        this.content = content;
        this.contentType = contentType;
        this.statusCode = statusCode;
        this.timeTaken = timeTaken;
    }

    public String getContent() {
        return content;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

}