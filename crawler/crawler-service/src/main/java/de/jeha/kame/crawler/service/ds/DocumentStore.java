package de.jeha.kame.crawler.service.ds;

import de.jeha.kame.crawler.core.types.CrawlResult;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * @author jenshadlich@googlemail.com
 */
public interface DocumentStore {

    /**
     * Persist the document of the given crawl result.
     *
     * @param crawlId   id
     * @param timestamp timestamp
     * @param result    crawl result
     * @throws IOException
     */
    void save(String crawlId, ZonedDateTime timestamp, CrawlResult result) throws IOException;

}
