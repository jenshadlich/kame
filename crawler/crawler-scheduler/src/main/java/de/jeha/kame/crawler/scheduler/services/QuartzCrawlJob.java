package de.jeha.kame.crawler.scheduler.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jeha.kame.crawler.core.http.ContentType;
import de.jeha.kame.crawler.core.http.Headers;
import de.jeha.kame.crawler.scheduler.model.CrawlJob;
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
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class QuartzCrawlJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzCrawlJob.class);

    public static final String CRAWL_JOB = "CRAWL_JOB";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CrawlJob crawlJob = (CrawlJob) context.getJobDetail().getJobDataMap().get(CRAWL_JOB);
        LOG.info("{}", crawlJob);

        ObjectMapper objectMapper = new ObjectMapper();
        CrawlRequest request = new CrawlRequest(crawlJob.getSeedUrl());

        HttpPost httpPost = new HttpPost("http://localhost:8080/crawl");
        httpPost.setHeader(Headers.CONTENT_TYPE, ContentType.APPLICATION_JSON.getValue());

        try {
            httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(request), "UTF-8"));
            HttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);

            String responseJson = EntityUtils.toString(httpResponse.getEntity());
            CrawlResponse response = objectMapper.readValue(responseJson, CrawlResponse.class);

            LOG.info("{}", response.getId());

        } catch (IOException e) {
            throw new JobExecutionException(e);
        }

    }

    public static class CrawlRequest {

        @JsonProperty
        private String url;

        public CrawlRequest(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CrawlResponse {

        @JsonProperty
        private String id;

        @JsonProperty
        private String date;

        @JsonProperty
        private Integer statusCode;

        @JsonProperty
        private List<String> links;

        public String getId() {
            return id;
        }

        public String getDate() {
            return date;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public List<String> getLinks() {
            return links;
        }

    }

}
