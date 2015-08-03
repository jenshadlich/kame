package de.jeha.kame.crawler.core;

import de.jeha.kame.crawler.common.http.ContentType;
import de.jeha.kame.crawler.common.http.StatusCode;
import de.jeha.kame.crawler.core.model.Page;
import de.jeha.kame.crawler.core.types.CrawlResult;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 */
public class AbstractLinkExtractorTest {

    protected String getResourceAsString(String resource) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(resource));
    }

    protected CrawlResult buildCrawlResult(String content) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", ContentType.TEXT_HTML.getValue());

        Page page = Page.Builder.New()
                .withUrl("")
                .withContent(content)
                .withHeaders(headers)
                .build();

        return new CrawlResult(page, content, StatusCode.SC_200.getCode(), 0);
    }

    protected Document getDocumentFromResource(String resource) throws IOException {
        return Jsoup.parse(getResourceAsString(resource));
    }

}
