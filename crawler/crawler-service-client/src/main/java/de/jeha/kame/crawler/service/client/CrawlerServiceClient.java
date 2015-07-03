package de.jeha.kame.crawler.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jeha.kame.crawler.core.http.ContentType;
import de.jeha.kame.crawler.core.http.Headers;
import de.jeha.kame.crawler.service.api.CrawlRequest;
import de.jeha.kame.crawler.service.api.CrawlResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlerServiceClient {

    private final String endpointUrl;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CrawlerServiceClient(String endpointUrl) {
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
