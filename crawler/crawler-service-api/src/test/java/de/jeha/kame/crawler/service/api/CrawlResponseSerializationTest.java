package de.jeha.kame.crawler.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author jenshadlich@googlemail.com
 */
public class CrawlResponseSerializationTest {

    @Test
    public void testSuccess() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        CrawlResponse input = CrawlResponse.withSuccess("id", "now", 200, Arrays.asList("http://www.example.org"));

        String json = objectMapper.writeValueAsString(input);

        CrawlResponse output = objectMapper.readValue(json, CrawlResponse.class);

        assertEquals(input.getId(), output.getId());
        assertEquals(input.getDate(), output.getDate());
        assertEquals(input.getStatusCode(), output.getStatusCode());
        assertEquals(input.getLinks().get(0), input.getLinks().get(0));

        assertEquals("id", output.getId());
        assertEquals("now", output.getDate());
        assertEquals(Integer.valueOf(200), output.getStatusCode());
        assertNull(output.getError());
        assertFalse(output.hasError());
    }

    @Test
    public void testError() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        CrawlResponse input = CrawlResponse.withError("id", "now", "an error occurred");

        String json = objectMapper.writeValueAsString(input);

        CrawlResponse output = objectMapper.readValue(json, CrawlResponse.class);

        assertEquals(input.getId(), output.getId());
        assertEquals(input.getDate(), output.getDate());
        assertEquals(input.getError().getMessage(), output.getError().getMessage());

        assertEquals("id", output.getId());
        assertEquals("now", output.getDate());
        assertTrue(output.hasError());
        assertEquals("an error occurred", output.getError().getMessage());

        assertNull(output.getLinks());
        assertNull(output.getStatusCode());
    }
}
