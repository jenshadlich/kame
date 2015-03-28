package de.jeha.kame.crawler;

import de.jeha.kame.crawler.types.CrawlResult;
import de.jeha.kame.crawler.http.ContentType;
import de.jeha.kame.crawler.http.StatusCode;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
public class AbstractResultParserTest {

    protected String getResourceAsString(String resource) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(resource));
    }

    protected CrawlResult buildCrawlResult(String content) {
        return new CrawlResult("", content, ContentType.TEXT_HTML.getValue(), StatusCode.SC_200.getCode(), 0);
    }

    protected CrawlResult buildCrawlResultFromResource(String resource) throws IOException {
        return buildCrawlResult(getResourceAsString(resource));
    }

}
