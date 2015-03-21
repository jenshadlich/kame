package de.jeha.kame.playground.crawler;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Crawler {

    private static final Logger LOG = LoggerFactory.getLogger(Crawler.class);
    private final String url;
    private static final int MAX = 100;

    public Crawler(String url) {
        this.url = url;
    }

    public static void main(String... args) throws IOException {
        Map<String, Boolean> links = new HashMap<String, Boolean>();
        links.put("http://www.spreadshirt.de", Boolean.FALSE);

        int cnt = 0;
        boolean done = false;
        while (!done) {
            Set<String> newLinks = new HashSet<String>();
            for (String link : links.keySet()) {
                boolean crawl = true;
                if (links.containsKey(link)) {
                    boolean alreadyCrawled = links.get(link);
                    if (alreadyCrawled) {
                        crawl = false;
                    }
                }
                if (crawl) {
                    cnt++;
                    if (cnt > MAX) {
                        done = true;
                        break;
                    }
                    CrawlResult r = new Crawler(link).execute();
                    links.put(link, Boolean.TRUE);

                    for (String rawNewLink : r.parse()) {
                        String newLink = StringEscapeUtils.escapeHtml4(StringUtils.trim(rawNewLink));
                        if (newLink.contains("www.spreadshirt.de")) { // TODO: filter
                            if (!newLinks.contains(newLink)) {
                                try { // TODO: check validity
                                    URI.create(newLink);
                                    newLinks.add(newLink);
                                } catch (IllegalArgumentException e) {
                                    LOG.warn("skip invalid url: '{}'", newLink);
                                }
                            }
                        }
                    }
                }
            }

            for (String newLink : newLinks) {
                links.put(newLink, Boolean.FALSE);
            }
        }
        LOG.info("{}", links.size());
    }

    public CrawlResult execute() throws IOException {
        System.out.println("crawling: " + url);

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        // Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)
        // Mozilla/5.0 (compatible; Kamebot/0.1)
        httpGet.setHeader("User-Agent", "");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        HttpResponse response = httpClient.execute(httpGet);

        try {
            StatusLine statusLine = response.getStatusLine();
            //LOG.info(statusLine.toString());

            HttpEntity entity = response.getEntity();
            String pageContent = IOUtils.toString(entity.getContent());
            //LOG.debug(pageContent);
            EntityUtils.consume(entity);

            stopWatch.stop();

            return new CrawlResult(url, pageContent, statusLine.getStatusCode(), stopWatch.getTime());
        } finally {
            httpGet.releaseConnection();
        }
    }
}