package de.jeha.kame.crawler;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerResultParseTest {

    @Test
    public void testSimple() throws IOException {
        String simple = IOUtils.toString(this.getClass().getResourceAsStream("/simple.html"));

        List<String> links = new CrawlResult("", simple, "", 200, 0).parse();
        assertEquals(1, links.size());
        assertEquals("http://www.google.de", links.get(0));
    }
}
