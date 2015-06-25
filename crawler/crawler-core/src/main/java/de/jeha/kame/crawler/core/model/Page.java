package de.jeha.kame.crawler.core.model;

/**
 * @author jenshadlich@googlemail.com
 */
public class Page {

    private final Domain domain;
    private final Hash hash;
    private final String url;

    public Page(Domain domain, String url) {
        this.domain = domain;
        this.hash = new Hash(url);
        this.url = url;
    }

    public Domain getDomain() {
        return domain;
    }

    public Hash getHash() {
        return hash;
    }

    public String getUrl() {
        return url;
    }

}
