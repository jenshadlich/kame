package de.jeha.kame.crawler.core.httpclient;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.conn.DnsResolver;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author jenshadlich@googlemail.com
 */
public class DefaultDnsResolver implements DnsResolver {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDnsResolver.class);

    private final DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;

    @Override
    public InetAddress[] resolve(String host) throws UnknownHostException {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        InetAddress[] result = dnsResolver.resolve(host);
        stopWatch.stop();

        LOG.debug("time for dns resolution for '{}': {} ms", host, stopWatch.getTime());

        return result;
    }

}
