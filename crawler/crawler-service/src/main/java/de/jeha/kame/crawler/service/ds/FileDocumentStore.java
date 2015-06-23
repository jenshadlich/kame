package de.jeha.kame.crawler.service.ds;

import com.codahale.metrics.health.HealthCheck;
import de.jeha.kame.crawler.core.types.CrawlResult;
import de.jeha.kame.crawler.service.ds.util.FileNameUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.zip.GZIPOutputStream;

/**
 * @author jenshadlich@googlemail.com
 */
public class FileDocumentStore implements DocumentStore {

    private static final Logger LOG = LoggerFactory.getLogger(FileDocumentStore.class);

    private final String path;

    public FileDocumentStore(String path) {
        this.path = path;

        try {
            File root = new File(path);
            if (!root.exists()) {
                FileUtils.forceMkdir(root);
            }
        } catch (IOException e) {
            LOG.error("Could not create root directory", e);
        }
    }

    @Override
    public void save(String crawlId, ZonedDateTime timestamp, CrawlResult result) throws IOException {
        final String filenamePrefix = String.format(
                "%s-%d-%s",
                crawlId,
                timestamp.toEpochSecond(),
                FileNameUtils.stripToFilename(result.getUrl())
        );

        saveDocument(filenamePrefix, result);
        saveMetaRecord(filenamePrefix, result);
    }

    private void saveDocument(String filenamePrefix, CrawlResult result) throws IOException {
        final String documentFilename = path + "/" + filenamePrefix + ".gz";
        try (FileOutputStream output = new FileOutputStream(documentFilename)) {
            try (Writer writer = new OutputStreamWriter(new GZIPOutputStream(output), "UTF-8")) {
                writer.write(result.getContent());
                LOG.debug("file '{}' written", documentFilename);
            }
        }
    }

    private void saveMetaRecord(String filenamePrefix, CrawlResult result) throws IOException {
        final String metaFilename = path + "/" + filenamePrefix + ".meta.json";
        final String metaRecord = String.format("{\n  url: %s\n}\n", StringEscapeUtils.escapeJson(result.getUrl()));

        FileUtils.writeStringToFile(new File(metaFilename), metaRecord);
        LOG.debug("file '{}' written", metaFilename);
    }

    @Override
    public HealthCheck.Result checkHealth() {
        File root = new File(path);
        return root.exists()
                ? HealthCheck.Result.healthy()
                : HealthCheck.Result.unhealthy("root path does not exist");
    }

}
