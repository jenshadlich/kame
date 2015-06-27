package de.jeha.kame.crawler.core.types;

import de.jeha.kame.crawler.core.model.Page;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlResult {

    private final Page page;
    private final String content;
    private final int statusCode;
    private final long timeTaken;

    public CrawlResult(Page page, String content, int statusCode, long timeTaken) {
        this.page = page;
        this.content = content;
        this.statusCode = statusCode;
        this.timeTaken = timeTaken;
    }

    public Page getPage() {
        return page;
    }

    public String getContent() {
        return content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

}