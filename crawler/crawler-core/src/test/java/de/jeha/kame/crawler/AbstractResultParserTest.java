package de.jeha.kame.crawler;

import de.jeha.kame.crawler.types.CrawlResult;
import de.jeha.kame.crawler.http.ContentType;
import de.jeha.kame.crawler.http.StatusCode;
import de.jeha.kame.crawler.types.Headers;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 */
public class AbstractResultParserTest {

    protected String getResourceAsString(String resource) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(resource));
    }

    protected CrawlResult buildCrawlResult(String content) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", ContentType.TEXT_HTML.getValue());

        return new CrawlResult(
                "",
                content,
                new CrawlResult.Metadata(
                        new Headers(headers),
                        StatusCode.SC_200.getCode(),
                        0
                )
        );
    }

    protected CrawlResult buildCrawlResultFromResource(String resource) throws IOException {
        return buildCrawlResult(getResourceAsString(resource));
    }

}
