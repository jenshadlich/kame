package de.jeha.kame.crawler.core.httpclient;

import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

/**
 * @author jenshadlich@googlemail.com
 */
public class HttpClientFactory {

    private static CloseableHttpClient INSTANCE;

    public synchronized static CloseableHttpClient getInstance() {
        if (INSTANCE == null) {
            HttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", SSLConnectionSocketFactory.getSocketFactory())
                            .build(),
                    null,
                    null,
                    new DefaultDnsResolver()
            );
            INSTANCE = HttpClients.custom().setConnectionManager(connectionManager).build();
        }

        return INSTANCE;
    }

}
