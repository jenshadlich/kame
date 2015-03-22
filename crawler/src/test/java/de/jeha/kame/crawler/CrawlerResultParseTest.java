package de.jeha.kame.crawler;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerResultParseTest extends AbstractCrawlerResultParseTest {

    @Test
    public void testSimple() throws IOException {
        List<String> links = buildCrawlResultFromResource("/simple.html").parse();

        assertEquals(1, links.size());
        assertEquals("http://www.google.com", links.get(0));
    }
}
