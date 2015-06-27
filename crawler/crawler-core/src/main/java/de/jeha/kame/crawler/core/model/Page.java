package de.jeha.kame.crawler.core.model;

import de.jeha.kame.crawler.core.LinkExtractor;
import de.jeha.kame.crawler.core.http.Headers;
import de.jeha.kame.crawler.core.robots.RobotsMetaContentExtractor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jenshadlich@googlemail.com
 */
public class Page {

    private final Domain domain;
    private final Hash hash;
    private final String url;
    private final Headers headers;
    private final List<Link> links;

    public Page(Domain domain, String url, Headers headers, List<Link> links) {
        this.domain = domain;
        this.hash = new Hash(url);
        this.url = url;
        this.headers = headers;
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

    public List<Link> getLinks() {
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
        private Headers headers;

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            this.headers = new Headers(headers);
            return this;
        }

        public Page build() {
            Document document = Jsoup.parse(content);
            List<Link> links = LINK_EXTRACTOR.get(document).stream().map(Link::new).collect(Collectors.toList());

            String domain = extractDomainName(url);
            return new Page(new Domain(domain), url, headers, links);
        }

        public static Builder New() {
            return new Builder();
        }

        private String extractDomainName(String url) {
            return StringUtils.substringBefore(StringUtils.substringAfter(url, "://"), "/").replaceFirst("^www.*?\\.", "");
        }

    }
}
