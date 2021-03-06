package de.jeha.kame.crawler.core;

import de.jeha.kame.crawler.core.httpclient.HttpClientFactory;
import de.jeha.kame.crawler.core.model.Page;
import de.jeha.kame.crawler.core.types.CrawlResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 */
public class Crawler {

    private static final Logger LOG = LoggerFactory.getLogger(Crawler.class);

    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (compatible; Kamebot/0.1)";

    private final String url;
    private final String userAgent;

    /**
     * A crawler with default user agent '{@value de.jeha.kame.crawler.core.Crawler#DEFAULT_USER_AGENT}'.
     *
     * @param url url to crawl
     */
    public Crawler(String url) {
        this(url, DEFAULT_USER_AGENT);
    }

    /**
     * @param url       url to crawl
     * @param userAgent custom user agent string
     */
    public Crawler(String url, String userAgent) {
        this.url = url;
        this.userAgent = userAgent;
    }

    /**
     * Crawl the url.
     *
     * @return crawl result
     * @throws IOException
     */
    public CrawlResult execute() throws IOException {
        LOG.info("Crawling: '{}'", url);

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", userAgent);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        HttpResponse response = HttpClientFactory.getInstance().execute(httpGet);

        try {
            StatusLine statusLine = response.getStatusLine();
            LOG.info(statusLine.toString());

            HttpEntity entity = response.getEntity();
            final String content = IOUtils.toString(entity.getContent());
            EntityUtils.consume(entity);
            final String contentType = entity.getContentType().getValue();
            LOG.info(contentType);

            stopWatch.stop();

            Map<String, String> headers = new HashMap<>();
            for (Header header : response.getAllHeaders()) {
                headers.put(header.getName(), header.getValue());
            }

            Page page = Page.Builder.New()
                    .withUrl(url)
                    .withContent(content)
                    .withHeaders(headers)
                    .build();

            return new CrawlResult(page, content, statusLine.getStatusCode(), stopWatch.getTime());
        } finally {
            httpGet.releaseConnection();
        }
    }
}