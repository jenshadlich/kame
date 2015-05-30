package de.jeha.kame.crawler.core;

import de.jeha.kame.crawler.core.types.CrawlResult;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class ResultParserTest extends AbstractResultParserTest {

    @Test
    public void test() {
        CrawlResult result = buildCrawlResult("");

        assertEquals(200, result.getMetadata().getStatusCode());
        assertEquals(0, result.getMetadata().getTimeTaken());
        assertEquals("text/html", result.getMetadata().getHeaders().getContentType());
    }

    @Test
    public void testSimple() throws IOException {
        List<String> links = new ResultParser().parse(buildCrawlResultFromResource("/simple.html"));

        assertEquals(1, links.size());
        assertEquals("http://www.google.com", links.get(0));
    }
}
