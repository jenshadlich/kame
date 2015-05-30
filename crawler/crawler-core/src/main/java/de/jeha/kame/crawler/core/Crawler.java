package de.jeha.kame.crawler.core;

import de.jeha.kame.crawler.core.types.CrawlResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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

    // Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)
    // TODO: setup external resource + add to user agent
    private static final String USER_AGENT = "Mozilla/5.0 (compatible; Kamebot/0.1)";

    private final String url;

    public Crawler(String url) {
        this.url = url;
    }

    public CrawlResult execute() throws IOException {
        LOG.info("Crawling: '{}'", url);

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", USER_AGENT);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        HttpResponse response = httpClient.execute(httpGet);

        try {
            StatusLine statusLine = response.getStatusLine();
            LOG.info(statusLine.toString());

            HttpEntity entity = response.getEntity();
            final String content = IOUtils.toString(entity.getContent());
            EntityUtils.consume(entity);
            final String contentType = entity.getContentType().getValue();
            LOG.info(contentType);

            Map<String, String> headers = new HashMap<>();
            for (Header header : response.getAllHeaders()) {
                headers.put(header.getName(), header.getValue());
            }

            stopWatch.stop();

            return new CrawlResult(
                    url,
                    content,
                    new CrawlResult.Metadata(headers, statusLine.getStatusCode(), stopWatch.getTime())
            );
        } finally {
            httpGet.releaseConnection();
        }
    }
}