package de.jeha.kame.crawler.service.config;

/**
 * @author jenshadlich@googlemail.com
 */
public class AmqpConfiguration {

    private final String host;

    private final int port;

    private final int connectionTimeout;

    public AmqpConfiguration(String host, int port, int connectionTimeout) {
        this.host = host;
        this.port = port;
        this.connectionTimeout = connectionTimeout;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

}
