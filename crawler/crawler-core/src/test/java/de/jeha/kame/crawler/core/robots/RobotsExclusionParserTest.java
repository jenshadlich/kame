package de.jeha.kame.crawler.core.robots;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsExclusionParserTest {

    @Test
    public void allRobotsAllowEverything() throws IOException {
        RobotsExclusion result =
                new RobotsExclusionParser().parse(getInputStreamForTest("all_robots_allow_everything_robots.txt"));

        assertTrue(result.allowed(UserAgents.ANY, Constants.ROOT_PATH));
        assertTrue(result.allowed("BadBot", "/"));
    }

    @Test
    public void allRobotsAllowNothing() throws IOException {
        RobotsExclusion result =
                new RobotsExclusionParser().parse(getInputStreamForTest("all_robots_allow_nothing_robots.txt"));

        assertFalse(result.allowed(UserAgents.ANY, "/"));
        assertTrue(result.disallowed(UserAgents.ANY, "/"));
    }

    @Test
    public void allRobotsDisallowSpecificDirectory() throws IOException {
        RobotsExclusion result =
                new RobotsExclusionParser().parse(getInputStreamForTest("all_robots_disallow_specific_directory_robots.txt"));

        // only this file is disallowed for all robots
        assertTrue(result.disallowed(UserAgents.ANY, "/disallowed/"));
        assertTrue(result.disallowed(UserAgents.ANY, "/disallowed/subdir/"));
        assertFalse(result.allowed("BadBot", "/disallowed/"));
        assertFalse(result.allowed("BadBot", "/disallowed/file.html"));

        // everything else is allowed
        assertTrue(result.allowed(UserAgents.ANY, "/"));
        assertTrue(result.allowed(UserAgents.ANY, "/foo"));
        assertTrue(result.allowed(UserAgents.ANY, "/foo/bar"));
        assertTrue(result.allowed("BadBot", "/"));
    }

    @Test
    public void allRobotsDisallowSpecificFile() throws IOException {
        RobotsExclusion result =
                new RobotsExclusionParser().parse(getInputStreamForTest("all_robots_disallow_specific_file_robots.txt"));

        // only this file is disallowed for all robots
        assertTrue(result.disallowed(UserAgents.ANY, "/d/file.html"));
        assertFalse(result.allowed("BadBot", "/d/file.html"));

        // everything else is allowed
        assertTrue(result.allowed(UserAgents.ANY, "/"));
        assertTrue(result.allowed("BadBot", "/"));
    }

    @Test
    public void stackoverflowRobots() throws IOException {
        RobotsExclusion result =
                new RobotsExclusionParser().parse(getInputStreamForTest("live-examples/stackoverflow.com_robots.txt"));

        assertTrue(result.disallowed("Slurp", "/"));
        assertTrue(result.disallowed("Slurp", "/foobar"));
        assertTrue(result.disallowed("Slurp", "/foobar/"));

        assertTrue(result.allowed("Googlebot", "/"));
        assertTrue(result.allowed("Googlebot", "/foobar"));
        assertTrue(result.allowed("Googlebot", "/foobar/"));
    }

    @Test
    public void googleRobots() throws IOException {
        RobotsExclusion result =
                new RobotsExclusionParser().parse(getInputStreamForTest("live-examples/google.com_robots.txt"));

        assertTrue(result.allowed(UserAgents.ANY, "/"));

        assertTrue(result.disallowed(UserAgents.ANY, "/globalmarketfinder/"));
        assertTrue(result.disallowed(UserAgents.ANY, "/globalmarketfinder/foo.bar"));
        assertTrue(result.disallowed(UserAgents.ANY, "/globalmarketfinder/foo/bar"));
        assertFalse(result.allowed(UserAgents.ANY, "/globalmarketfinder/foobar.html")); // TODO: should be true if Allow is supported

        assertEquals(17, result.getSitemaps().size());
        assertEquals("https://www.google.com/edu/sitemap.xml", result.getSitemaps().get(1));
    }

    @Test
    public void spreadshirtRobots() throws IOException {
        RobotsExclusion result =
                new RobotsExclusionParser().parse(getInputStreamForTest("live-examples/spreadshirt.de_robots.txt"));

        assertTrue(result.allowed(UserAgents.ANY, "/"));
        assertTrue(result.disallowed("Yandex", "/"));
        assertTrue(result.disallowed(UserAgents.ANY, "/de/DE/Widget/"));

        // /*-C6220*
        assertTrue(result.disallowed(UserAgents.ANY, "/-C6220"));
        assertTrue(result.disallowed(UserAgents.ANY, "/foo-C6220bar"));
        assertTrue(result.disallowed(UserAgents.ANY, "/1/foo-C6220bar"));
    }

    // -----------------------------------------------------------------------------------------------------------------

    private InputStream getInputStreamForTest(String resource) {
        return this.getClass().getResourceAsStream("/robots/" + resource);
    }

}
