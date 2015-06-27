package de.jeha.kame.crawler.core;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class LinkExtractor {

    public List<String> get(Document document) {
        final List<String> links = new ArrayList<>();

        for (Element e : document.select("a[href]")) {
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
