package de.jeha.kame.crawler.core.model;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jenshadlich@googlemail.com
 */
public class Hash {

    private static final MessageDigest MESSAGE_DIGEST;
    private static final String NULL_OR_EMPTY_STRING_HASH;

    static {
        try {
            MESSAGE_DIGEST = MessageDigest.getInstance("SHA-256");
            NULL_OR_EMPTY_STRING_HASH = Hex.encodeHexString(MESSAGE_DIGEST.digest("".getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    private final String value;

    public Hash(String input) {
        if (input == null || input.length() == 0) {
            this.value = NULL_OR_EMPTY_STRING_HASH;
        } else {
            byte[] digest = MESSAGE_DIGEST.digest(input.getBytes(Charset.forName("UTF-8")));
            this.value = Hex.encodeHexString(digest);
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
