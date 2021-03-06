package de.jeha.kame.crawler.core.robots;

import de.jeha.kame.crawler.core.robots.util.MatchUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsExclusion {

    private final static List<String> EMPTY_STRING_LIST;

    static {
        EMPTY_STRING_LIST = new ArrayList<>();
        EMPTY_STRING_LIST.add("");
    }

    private final Map<String, List<String>> disallowMap;
    private final List<String> sitemaps;

    public RobotsExclusion(Map<String, List<String>> disallowMap, List<String> sitemaps) {
        this.disallowMap = disallowMap;
        this.sitemaps = sitemaps;
        if (disallowMap.isEmpty()) {
            this.disallowMap.put(UserAgents.ANY, EMPTY_STRING_LIST);
        }
    }

    /**
     * Check if a path is allow for a user agent.
     *
     * @param userAgent user agent
     * @param path      path
     * @return true if allowed
     */
    public boolean allowed(String userAgent, String path) {
        return !disallowed(userAgent, path); // naive implementation
    }

    /**
     * Check if a path is NOT allow for a user agent.
     *
     * @param userAgent user agent
     * @param path      path
     * @return true if NOT allowed
     */
    public boolean disallowed(String userAgent, String path) {
        boolean disallowed;

        if (!UserAgents.ANY.equals(userAgent)) {
            disallowed = isDisallowed(userAgent, path);
            if (!disallowed) {
                disallowed = isDisallowed(UserAgents.ANY, path);
            }
        } else {
            disallowed = isDisallowed(userAgent, path);
        }
        return disallowed;
    }

    private boolean isDisallowed(String userAgent, String path) {
        // test specified userAgent
        if (disallowMap.containsKey(userAgent.toLowerCase())) {
            for (String disallowedPath : disallowMap.get(userAgent.toLowerCase())) {
                if ("".equals(disallowedPath)) {
                    return false;
                }
                // exact match
                if (StringUtils.equalsIgnoreCase(path, disallowedPath)) {
                    return true;
                }
                // disallow subdirectories
                if (disallowedPath.endsWith("/") && StringUtils.startsWithIgnoreCase(path, disallowedPath)) {
                    return true;
                }
                if (MatchUtils.wildcardMatch(path, disallowedPath)) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<String> getSitemaps() {
        return sitemaps;
    }

}
