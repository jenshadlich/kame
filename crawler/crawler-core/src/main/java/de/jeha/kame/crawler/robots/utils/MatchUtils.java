package de.jeha.kame.crawler.robots.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jenshadlich@googlemail.com
 */
public class MatchUtils {

    /**
     * @param text    text to match against pattern
     * @param pattern pattern with wildcard
     * @return true if text match the pattern
     */
    public static boolean wildcardMatch(final String text, final String pattern) {
        String[] parts = StringUtils.split(pattern, '*');
        String remainder = text;

        // no match possible if the 'text' does not start with the first part
        if (parts.length > 0 && !remainder.startsWith(parts[0])) {
            return false;
        }

        for (String part : parts) {
            int idx = remainder.indexOf(part);

            if (idx == -1) {
                return false;
            }
            remainder = remainder.substring(idx + part.length());
        }

        return true;
    }

}
