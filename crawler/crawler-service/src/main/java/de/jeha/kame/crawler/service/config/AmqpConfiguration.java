package de.jeha.kame.crawler.service.config;

/**
 * @author jenshadlich@googlemail.com
 */
public class AmqpConfiguration {

    private final String host;

    private final int port;

    public AmqpConfiguration(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

}
