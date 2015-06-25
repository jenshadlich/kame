package de.jeha.kame.crawler.core.model;

import de.jeha.kame.crawler.core.LinkExtractor;
import de.jeha.kame.crawler.core.robots.RobotsMetaContentExtractor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class Page {

    private final Domain domain;
    private final Hash hash;
    private final String url;
    private final List<String> links;

    public Page(Domain domain, String url, List<String> links) {
        this.domain = domain;
        this.hash = new Hash(url);
        this.url = url;
        this.links = links;
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

    public List<String> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static class Builder {

        private static final LinkExtractor LINK_EXTRACTOR = new LinkExtractor();
        private static final RobotsMetaContentExtractor ROBOTS_META_CONTENT_EXTRACTOR = new RobotsMetaContentExtractor();

        private String url;
        private String content;

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Page build() {
            Document document = Jsoup.parse(content);
            List<String> links = LINK_EXTRACTOR.get(document);

            String domain = extractDomainName(url);
            return new Page(new Domain(domain), url, links);
        }

        public static Builder New() {
            return new Builder();
        }

        private String extractDomainName(String url) {
            return StringUtils.substringBefore(StringUtils.substringAfter(url, "://"), "/").replaceFirst("^www.*?\\.", "");
        }

    }
}
