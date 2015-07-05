package de.jeha.kame.crawler.core.model;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author jenshadlich@googlemail.com
 */
public class PageBuilderTest {

    @Test
    public void test() throws IOException {
        String url = "http://www.foo.bar/baz";
        String content = IOUtils.toString(this.getClass().getResourceAsStream("/simple.html"));

        Page page = Page.Builder.New().withUrl(url).withContent(content).build();

        assertEquals("foo.bar", page.getDomain().getName());
        assertEquals(1, page.getLinks().size());
        assertNull(page.getCanonicalLink());
    }

    @Test
    public void testCanonicalLink() throws IOException {
        String url = "http://www.foo.bar/baz";
        String content = IOUtils.toString(this.getClass().getResourceAsStream("/simple_with_canonical_link.html"));

        Page page = Page.Builder.New().withUrl(url).withContent(content).build();

        assertEquals("simple_with_canonical_link.html", page.getCanonicalLink());
    }
}
