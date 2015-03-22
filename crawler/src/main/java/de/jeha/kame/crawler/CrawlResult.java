package de.jeha.kame.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlResult {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlResult.class);

    private final String url;
    private final String content;
    private final String contentType;
    private final int statusCode;
    private final long timeTaken;

    public CrawlResult(String url, String content, String contentType, int statusCode, long timeTaken) {
        this.url = url;
        this.content = content;
        this.contentType = contentType;
        this.statusCode = statusCode;
        this.timeTaken = timeTaken;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

    public List<String> parse() {
        List<String> ahrefs = new ArrayList<>();

        if (this.content == null) {
            throw new IllegalStateException("pageContent must not be null");
        }

        Document doc = Jsoup.parse(this.content);
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