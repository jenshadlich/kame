package de.jeha.kame.crawler.service.ds.util;

import java.util.stream.Collector;

/**
 * @author jenshadlich@googlemail.com
 */
public class FileNameUtils {

    public static String stripToFilename(String string) {
        if (string == null) {
            return null;
        }
        return string.chars()
                .mapToObj(i -> Character.isAlphabetic(i) ? (char) i : (((char) i) == '/' ? '#' : '_'))
                .collect(Collector.of(StringBuilder::new, (sb, s) -> sb.append(s), StringBuilder::append, StringBuilder::toString));
    }

}
