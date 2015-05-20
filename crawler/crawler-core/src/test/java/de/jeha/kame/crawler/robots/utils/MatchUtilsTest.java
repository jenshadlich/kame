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
        assertFalse(MatchUtils.wildcardMatch("/g/foobar.xml", "/g/*.html"));
        assertFalse(MatchUtils.wildcardMatch("/f/g/foobar.html", "/g/*.html"));
    }

}
