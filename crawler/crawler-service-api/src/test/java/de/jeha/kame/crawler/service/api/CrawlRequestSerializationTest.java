package de.jeha.kame.crawler.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlRequestSerializationTest {

    @Test
    public void test() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String url = "http://www.example.org";

        CrawlRequest input = new CrawlRequest(url);

        String json = objectMapper.writeValueAsString(input);

        CrawlRequest output = objectMapper.readValue(json, CrawlRequest.class);

        assertEquals(input.getUrl(), output.getUrl());
        assertEquals(url, output.getUrl());
    }

}
