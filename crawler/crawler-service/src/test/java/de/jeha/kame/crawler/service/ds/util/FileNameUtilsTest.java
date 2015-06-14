package de.jeha.kame.crawler.service.ds.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class FileNameUtilsTest {

    @Test
    public void testStripToFilename() {
        Assert.assertNull(FileNameUtils.stripToFilename(null));
        assertEquals("foo", FileNameUtils.stripToFilename("foo"));
        assertEquals("http_##www_foo_bar#baz", FileNameUtils.stripToFilename("http://www.foo.bar/baz"));
    }

}
