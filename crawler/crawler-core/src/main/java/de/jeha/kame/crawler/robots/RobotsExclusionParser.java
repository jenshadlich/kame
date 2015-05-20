package de.jeha.kame.crawler.robots;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parser for robots.txt files. Currently limited to the original standard which means it does only support User-agent
 * and Disallow instructions (no Allow, Crawl-Delay or Sitemap).
 *
 * See http://www.robotstxt.org/
 *
 * @author jenshadlich@googlemail.com
 * @todo support Allow, Crawl-Delay, Sitemap instructions
 */
public class RobotsExclusionParser {

    private static final Logger LOG = LoggerFactory.getLogger(RobotsExclusionParser.class);

    /**
     * Parse a given robots.txt file given as InputStream.
     *
     * @param robotsFileInputStream robots.txt
     * @return robots.txt object model to query
     * @throws IOException
     */
    public RobotsExclusion parse(InputStream robotsFileInputStream) throws IOException {
        List<String> lines = IOUtils.readLines(robotsFileInputStream);

        Map<String, List<String>> disallowMap = new HashMap<>();
        int lineCounter = 0;
        String currentUserAgent = null;

        for (String line : lines) {
            ++lineCounter;
            final String trimmedLine = line.trim();
            if (trimmedLine.startsWith(Constants.COMMENT_DELIM)) {
                continue;
            }
            final String trimmedLineWithoutComments = StringUtils.substringBefore(trimmedLine, Constants.COMMENT_DELIM);

            if (trimmedLineWithoutComments.isEmpty()) {
                continue;
            }

            String parts[] = trimmedLineWithoutComments.split(Constants.FIELD_DELIM);
            if (parts.length < 1 || parts.length > 2) {
                LOG.warn("skip line {}, illegal format: '{}'", lineCounter, line);
                continue;
            }
            final String field = parts[0].trim().toLowerCase();
            final String value = (parts.length > 1) ? parts[1].trim() : "";

            switch (field) {
                case Constants.USER_AGENT:
                    currentUserAgent = value.toLowerCase();
                    break;
                case Constants.DISALLOW:
                    if (currentUserAgent == null) {
                        LOG.warn("skip line {}, " + Constants.DISALLOW + " without prior " + Constants.USER_AGENT, lineCounter);
                    } else {
                        if (!disallowMap.containsKey(currentUserAgent)) {
                            disallowMap.put(currentUserAgent, new ArrayList<>());
                        }
                        disallowMap.get(currentUserAgent).add(value);
                    }
                    break;
                // currently unsupported, skip
                case Constants.ALLOW:
                case Constants.CRAWL_DELAY:
                case Constants.SITEMAP:
                    LOG.info("skip line {}, unsupported instruction: '{}'", lineCounter, line);
                    break;
                default:
                    LOG.warn("skip line {}, unknown instruction: '{}'", lineCounter, line);
                    break;
            }
        }
        return new RobotsExclusion(disallowMap);
    }
}
