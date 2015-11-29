package de.jeha.kame.crawler.core.robots;

import de.jeha.kame.crawler.common.http.Headers;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsMetaContentExtractor {

    public static RobotsMetaContent get(Document document, Headers headers) {
        boolean metaRobotsSet = false;
        boolean robotsTagSet = false;
        boolean index = true;
        boolean follow = true;
        boolean archive = true;

        Elements elements = document.select("meta[name=robots]");
        if (!elements.isEmpty()) {
            metaRobotsSet = true;
            final String metaContent = elements.first().attr("content");

            index = !StringUtils.containsIgnoreCase(metaContent, "noindex");
            follow = !StringUtils.containsIgnoreCase(metaContent, "nofollow");
            archive = !StringUtils.containsIgnoreCase(metaContent, "noarchive");
        }

        String robotsTag = headers.get(Headers.X_ROBOTS_TAG);
        if (robotsTag != null) {
            robotsTagSet = true;

            index = !StringUtils.containsIgnoreCase(robotsTag, "noindex");
            follow = !StringUtils.containsIgnoreCase(robotsTag, "nofollow");
            archive = !StringUtils.containsIgnoreCase(robotsTag, "noarchive");
        }

        return new RobotsMetaContent(metaRobotsSet, robotsTagSet, index, follow, archive);
    }

}
