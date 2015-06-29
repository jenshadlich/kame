package de.jeha.kame.crawler.core.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class HashTest {

    @Test
    public void testNull() {
        Hash hash = new Hash(null);
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", hash.getValue());
    }

    @Test
    public void testEmptyString() {
        Hash hash = new Hash("");
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", hash.getValue());
    }

    @Test
    public void test() {
        Hash hash = new Hash("foobar");
        assertEquals("c3ab8ff13720e8ad9047dd39466b3c8974e592c2fa383d4a3960714caef0c4f2", hash.getValue());
    }

}
