package de.jeha.kame.crawler.core.robots;

import de.jeha.kame.crawler.core.types.CrawlResult;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsMetaExtractor {

    public RobotsMetaContent get(CrawlResult crawlResult) {
        final String content = crawlResult.getContent();

        boolean index = true;
        boolean follow = true;

        if (content == null) {
            throw new IllegalStateException("content must not be null");
        }

        Document doc = Jsoup.parse(content);

        Elements elements = doc.select("meta[name=robots]");
        if (!elements.isEmpty()) {
            final String metaContent = elements.first().attr("content");

            if (StringUtils.containsIgnoreCase(metaContent, "noindex")) {
                index = false;
            }

            if (StringUtils.containsIgnoreCase(metaContent, "nofollow")) {
                follow = false;
            }
        }

        return new RobotsMetaContent(index, follow, true);
    }

}
