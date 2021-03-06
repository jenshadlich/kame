package de.jeha.kame.crawler.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.jeha.kame.crawler.service.ds.DocumentStore;
import de.jeha.kame.crawler.service.ds.FileDocumentStore;

/**
 * @author jenshadlich@googlemail.com
 */
@JsonTypeName("file")
public class FileDocumentStoreFactory implements DocumentStoreFactory {

    @JsonProperty
    private String path;

    private transient FileDocumentStore fileDocumentStore;

    @Override
    public DocumentStore build() {
        if (fileDocumentStore == null) {
            fileDocumentStore = new FileDocumentStore(path);
        }
        return fileDocumentStore;
    }

}
