package de.jeha.kame.crawler.core;

import de.jeha.kame.crawler.core.types.CrawlResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class LinkExtractor {

    public List<String> get(CrawlResult crawlResult) {

        final List<String> links = new ArrayList<>();

        if (!crawlResult.hasContent()) {
            throw new IllegalStateException("content must not be null");
        }

        Document doc = Jsoup.parse(crawlResult.getContent());

        for (Element e : doc.select("a[href]")) {
            final String href = e.attr("href");

            if ("#".equals(href)) {
                continue;
            }
            if (href != null && href.startsWith("#")) {
                continue;
            }

            links.add(e.attr("href"));
        }

        return links;
    }
}
