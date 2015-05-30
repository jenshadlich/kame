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
public class ResultParser {

    public List<String> parse(CrawlResult crawlResult) {
        final String content = crawlResult.getContent();
        List<String> ahrefs = new ArrayList<>();

        if (content == null) {
            throw new IllegalStateException("content must not be null");
        }

        Document doc = Jsoup.parse(content);
        for (Element e : doc.select("a[href]")) {
            //LOG.trace(e.toString());

//            if ("_blank".equals(e.attr("target"))) {
//                continue;
//            }
            if ("#".equals(e.attr("href"))) {
                continue;
            }
            if (e.attr("href") != null && e.attr("href").startsWith("#")) {
                continue;
            }
            if (e.attr("href").startsWith("http://www.example.com")) { // TODO: exclude at upper level
                continue;
            }
            ahrefs.add(e.attr("href"));
        }
        //LOG.debug("#hrefs = {}", ahrefs.size());

        return ahrefs;
    }
}
