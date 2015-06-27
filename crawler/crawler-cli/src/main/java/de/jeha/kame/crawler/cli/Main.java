package de.jeha.kame.crawler.cli;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import de.jeha.kame.crawler.cli.dao.UrlDAO;
import de.jeha.kame.crawler.core.Crawler;
import de.jeha.kame.crawler.core.LinkExtractor;
import de.jeha.kame.crawler.core.model.Link;
import de.jeha.kame.crawler.core.types.CrawlResult;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author jenshadlich@googlemail.com
 */
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final int MAX = 1000;
    private static final boolean SAME_DOMAIN = true;
    private static final String URL = "www.spreadshirt.de";

    public static void main(String... args) throws IOException {

        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
        final DB crawlerDB = mongoClient.getDB("crawler");

        final UrlDAO urlDAO = new UrlDAO(crawlerDB);

        Map<String, Boolean> links = new HashMap<>();
        links.put("http://" + URL, Boolean.FALSE);

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
                    CrawlResult result = new Crawler(link).execute();
                    LOG.debug("Status code: {}", result.getStatusCode());

                    links.put(link, Boolean.TRUE);

                    LinkExtractor linkExtractor = new LinkExtractor();

                    for (Link rawNewLink : result.getPage().getLinks()) {
                        String newLink = StringEscapeUtils.escapeHtml4(StringUtils.trim(rawNewLink.getValue()));

                        if (SAME_DOMAIN && !newLink.contains(URL)) {
                            LOG.debug("Skip: {}", newLink);
                            continue;
                        }
                        if (!newLinks.contains(newLink)) {
                            try { // TODO: check validity
                                URI uri = URI.create(newLink);
                                LOG.debug(uri.toString());
                                newLinks.add(newLink);
                                urlDAO.addUrl(newLink);
                            } catch (IllegalArgumentException e) {
                                LOG.warn("skip invalid url: '{}'", newLink);
                            }
                        }
                    }
                }
            }

            for (String newLink : newLinks) {
                links.put(newLink, Boolean.FALSE);
            }
        }
        LOG.info("Number of links: {}", links.size());
    }
}