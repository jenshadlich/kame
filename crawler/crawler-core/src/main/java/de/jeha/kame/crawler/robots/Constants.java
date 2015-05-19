package de.jeha.kame.crawler.robots;

/**
 * @author jenshadlich@googlemail.com
 */
public final class Constants {

    public static final String ROBOTS_FILE = "/robots.txt";

    public static final String USER_AGENT = "user-agent"; // field "User-agent", lower case
    public static final String DISALLOW = "disallow"; // field "Disallow", lower case

    // currently unsupported nonstandard extensions:
    // Crawl-delay
    // Allow
    // Sitemap

    public static final String COMMENT_DELIM = "#";
    public static final String FIELD_DELIM = ":";

    public static final String ROOT_PATH = "/";
}
