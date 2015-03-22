package de.jeha.kame.crawler;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
public class AbstractCrawlerResultParseTest {

    protected String getResourceAsString(String resource) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(resource));
    }

    protected CrawlResult buildCrawlResult(String content) {
        return new CrawlResult("", content, "", 200, 0);
    }

    protected CrawlResult buildCrawlResultFromResource(String resource) throws IOException {
        return buildCrawlResult(getResourceAsString(resource));
    }

}
