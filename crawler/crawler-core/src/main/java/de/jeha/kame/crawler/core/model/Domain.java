package de.jeha.kame.crawler.core.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author jenshadlich@googlemail.com
 */
public class Domain {

    private final Hash hash;
    private final String name;

    public Domain(String name) {
        this.name = name;
        this.hash = new Hash(name);
    }

    public Hash getHash() {
        return hash;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
