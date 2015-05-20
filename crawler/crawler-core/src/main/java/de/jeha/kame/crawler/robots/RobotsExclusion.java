package de.jeha.kame.crawler.robots;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsExclusion {

    private final Map<String, List<String>> disallowMap;

    public RobotsExclusion(Map<String, List<String>> disallowMap) {
        this.disallowMap = disallowMap;
        if (disallowMap.isEmpty()) {
            this.disallowMap.put(UserAgents.ANY, Arrays.asList(""));
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
                // exact match
                if (StringUtils.equalsIgnoreCase(path, disallowedPath)) {
                    return true;
                }
                // disallow subdirectories
                if (disallowedPath.endsWith("/") && StringUtils.startsWithIgnoreCase(path, disallowedPath)) {
                    return true;
                }
            }
        }

        return false;
    }

}
