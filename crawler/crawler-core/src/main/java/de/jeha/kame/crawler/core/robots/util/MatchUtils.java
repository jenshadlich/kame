package de.jeha.kame.crawler.core.robots.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jenshadlich@googlemail.com
 */
public class MatchUtils {

    private static final char WILDCARD_CHAR = '*';
    private static final String WILDCARD_STRING = "" + WILDCARD_CHAR;
    private static final String DOLLAR_SIGN = "$";

    /**
     * @param text    text to match against pattern
     * @param pattern pattern with wildcard
     * @return true if text match the pattern
     */
    public static boolean wildcardMatch(final String text, final String pattern) {
        String[] parts = StringUtils.split(pattern, WILDCARD_CHAR);
        String remainder = text;

        // no match possible if the 'text' does not start with the first part
        if (parts.length > 0 && !pattern.startsWith(WILDCARD_STRING) && !remainder.startsWith(parts[0])) {
            return false;
        }

        for (String part : parts) {
            boolean hasEndChar = part.endsWith(DOLLAR_SIGN);

            if (hasEndChar) {
                part = StringUtils.substringBefore(part, DOLLAR_SIGN);
            }

            int idx = remainder.indexOf(part);

            if (idx == -1) {
                return false;
            }

            remainder = remainder.substring(idx + part.length());

            if (hasEndChar && remainder.length() > 1) {
                return false;
            }
        }

        // if the pattern does not end with a wildcard the remainder must be empty
        return remainder.isEmpty() || pattern.endsWith(WILDCARD_STRING);
    }

}
