package de.jeha.kame.crawler.core.robots;

import de.jeha.kame.crawler.core.types.CrawlResult;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsMetaExtractorTest {

    @Test
    public void testDefault() throws IOException {
        final String content = IOUtils.toString(this.getClass().getResourceAsStream("/simple.html"));
        RobotsMetaContent meta = new RobotsMetaExtractor().get(new CrawlResult(null, content, null));

        assertTrue(meta.isIndex());
        assertTrue(meta.isFollow());
        assertTrue(meta.isArchive());
    }

    @Test
    public void testNoIndexNoFollow() throws IOException {
        final String content =
                IOUtils.toString(this.getClass().getResourceAsStream("/robots/meta_robots_noindex_nofollow.html"));
        RobotsMetaContent meta = new RobotsMetaExtractor().get(new CrawlResult(null, content, null));

        assertFalse(meta.isIndex());
        assertFalse(meta.isFollow());
        assertTrue(meta.isArchive());
    }

}
