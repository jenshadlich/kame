package de.jeha.kame.crawler.core.model;

/**
 * @author jenshadlich@googlemail.com
 */
public class Domain {

    private final Hash hash;
    private final String domain;

    public Domain(String domain) {
        this.domain = domain;
        this.hash = new Hash(domain);
    }

    public Hash getHash() {
        return hash;
    }

    public String getDomain() {
        return domain;
    }

}
