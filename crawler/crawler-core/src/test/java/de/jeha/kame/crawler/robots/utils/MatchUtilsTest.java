package de.jeha.kame.crawler.robots.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jenshadlich@googlemail.com
 */
public class MatchUtilsTest {

    @Test
    public void test() {
        assertTrue(MatchUtils.wildcardMatch("/g/foobar.html", "/g/*.html"));
        assertTrue(MatchUtils.wildcardMatch("wildcard", "w*ldc*rd"));
        assertTrue(MatchUtils.wildcardMatch("wildcard", "*i***a**"));

        assertFalse(MatchUtils.wildcardMatch("/g/foobar.xml", "/g/*.html"));
        assertFalse(MatchUtils.wildcardMatch("/f/g/foobar.html", "/g/*.html"));
        assertFalse(MatchUtils.wildcardMatch("/g/foobar.htmlb", "/g/*.html"));

        assertTrue(MatchUtils.wildcardMatch("/f.pdf", "/*.pdf$"));
        assertFalse(MatchUtils.wildcardMatch("/f.pdfa", "/*.pdf$"));
    }

}
