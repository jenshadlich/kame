package de.jeha.kame.crawler.core.robots;

import de.jeha.kame.crawler.common.http.Headers;
import de.jeha.kame.crawler.core.model.Page;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsMetaContentExtractorTest {

    @Test
    public void testDefault() throws IOException {
        final String content = IOUtils.toString(this.getClass().getResourceAsStream("/simple.html"));

        Page page = Page.Builder.New().withContent(content).build();
        RobotsMetaContent meta = RobotsMetaContentExtractor.get(page.getDocument(), page.getHeaders());

        assertTrue(meta.isIndex());
        assertTrue(meta.isFollow());
        assertTrue(meta.isArchive());
        assertFalse(meta.isMetaRobotsSet());
        assertFalse(meta.isRobotsTagSet());
    }

    @Test
    public void testRobotsTag() throws IOException {
        final String content = IOUtils.toString(this.getClass().getResourceAsStream("/simple.html"));
        Map<String, String> headers = new HashMap<>();
        headers.put(Headers.X_ROBOTS_TAG, "noindex");

        Page page = Page.Builder.New()
                .withContent(content)
                .withHeaders(headers)
                .build();

        RobotsMetaContent meta = RobotsMetaContentExtractor.get(page.getDocument(), page.getHeaders());

        assertFalse(meta.isIndex());
        assertTrue(meta.isFollow());
        assertTrue(meta.isArchive());
        assertFalse(meta.isMetaRobotsSet());
        assertTrue(meta.isRobotsTagSet());
    }

    @Test
    public void testNoIndexNoFollow() throws IOException {
        final String content =
                IOUtils.toString(this.getClass().getResourceAsStream("/robots/meta_robots_noindex_nofollow.html"));

        Page page = Page.Builder.New().withContent(content).build();

        RobotsMetaContent meta = RobotsMetaContentExtractor.get(page.getDocument(), page.getHeaders());

        assertFalse(meta.isIndex());
        assertFalse(meta.isFollow());
        assertTrue(meta.isArchive());
        assertTrue(meta.isMetaRobotsSet());
        assertFalse(meta.isRobotsTagSet());
    }

}
