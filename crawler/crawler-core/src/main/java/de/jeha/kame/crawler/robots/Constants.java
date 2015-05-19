package de.jeha.kame.crawler.robots;

/**
 * @author jenshadlich@googlemail.com
 */
public final class Constants {

    public static final String ROBOTS_FILE = "/robots.txt";

    public static final String USER_AGENT = "user-agent"; // field "User-agent"
    public static final String DISALLOW = "disallow"; // field "Disallow"

    // currently unsupported nonstandard extensions:
    public static final String ALLOW = "allow"; // field "Allow"
    public static final String CRAWL_DELAY = "crawl-delay"; // field "Crawl-delay"
    public static final String SITEMAP = "sitemap"; // field "Sitemap"

    public static final String COMMENT_DELIM = "#";
    public static final String FIELD_DELIM = ":";

    public static final String ROOT_PATH = "/";
}
