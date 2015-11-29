package de.jeha.kame.crawler.core;

import de.jeha.kame.crawler.core.types.CrawlResult;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class LinkExtractorTest extends AbstractLinkExtractorTest {

    @Test
    public void testCrawlResult() {
        CrawlResult result = buildCrawlResult("");

        assertEquals(200, result.getStatusCode());
        assertEquals(0, result.getTimeTaken());
        assertEquals("text/html", result.getPage().getHeaders().getContentType());
    }

    @Test
    public void testSimple() throws IOException {
        List<String> links = LinkExtractor.get(getDocumentFromResource("/simple.html"));

        assertEquals(1, links.size());
        assertEquals("http://www.google.com", links.get(0));
    }
}
