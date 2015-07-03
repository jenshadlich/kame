package de.jeha.kame.crawler.scheduler.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jeha.kame.crawler.core.http.ContentType;
import de.jeha.kame.crawler.core.http.Headers;
import de.jeha.kame.crawler.scheduler.model.CrawlJob;
import de.jeha.kame.crawler.service.api.CrawlRequest;
import de.jeha.kame.crawler.service.api.CrawlResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
public class QuartzCrawlJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzCrawlJob.class);

    public static final String CRAWL_JOB = "CRAWL_JOB";

    private final CrawlerService crawlerService = new CrawlerService("http://localhost:8080/crawl");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CrawlJob crawlJob = (CrawlJob) context.getJobDetail().getJobDataMap().get(CRAWL_JOB);
        LOG.info("{}", crawlJob);

        try {
            CrawlResponse response = crawlerService.crawl(new CrawlRequest(crawlJob.getSeedUrl()));
            LOG.info("{}", response.getId());
        } catch (IOException e) {
            throw new JobExecutionException(e);
        }

    }

    // -----------------------------------------------------------------------------------------------------------------

    static class CrawlerService {

        private final String endpointUrl;
        private final ObjectMapper objectMapper = new ObjectMapper();

        public CrawlerService(String endpointUrl) {
            this.endpointUrl = endpointUrl;
        }

        public CrawlResponse crawl(CrawlRequest request) throws IOException {
            HttpPost httpPost = new HttpPost(endpointUrl + "/crawl");
            httpPost.setHeader(Headers.CONTENT_TYPE, ContentType.APPLICATION_JSON.getValue());

            httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(request), "UTF-8"));
            HttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);

            String responseJson = EntityUtils.toString(httpResponse.getEntity());
            return objectMapper.readValue(responseJson, CrawlResponse.class);
        }

    }

}
