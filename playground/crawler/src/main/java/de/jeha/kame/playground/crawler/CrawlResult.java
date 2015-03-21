package de.jeha.kame.playground.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CrawlResult {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlResult.class);

    private final String url;
    private final String pageContent;
    private final int statusCode;
    private final long timeTaken;

    public CrawlResult(String url, String pageContent, int statusCode, long timeTaken) {
        this.url = url;
        this.pageContent = pageContent;
        this.statusCode = statusCode;
        this.timeTaken = timeTaken;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public List<String> parse() {
        List<String> ahrefs = new ArrayList<String>();

        if (this.pageContent == null) {
            throw new IllegalStateException("pageContent must not be null");
        }

        Document doc = Jsoup.parse(this.pageContent);
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
            if (e.attr("href").startsWith("http://www.example.com")) {
                continue;
            }
            ahrefs.add(e.attr("href"));
        }
        //LOG.debug("#hrefs = {}", ahrefs.size());

        return ahrefs;
    }
}