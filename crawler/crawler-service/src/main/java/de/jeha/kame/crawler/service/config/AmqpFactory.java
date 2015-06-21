package de.jeha.kame.crawler.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rabbitmq.client.ConnectionFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author jenshadlich@googlemail.com
 */
public class AmqpFactory {

    @NotEmpty
    @JsonProperty
    private String host;

    @Min(1)
    @Max(65535)
    @JsonProperty
    private int port = ConnectionFactory.DEFAULT_AMQP_PORT;

    @JsonProperty
    private int connectionTimeout;

    private transient ConnectionFactory connectionFactory;

    public AmqpConfiguration build() {
        return new AmqpConfiguration(host, port, connectionTimeout);
    }

    public ConnectionFactory getConnectionFactory() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setConnectionTimeout(connectionTimeout);
            connectionFactory.setHost(host);
            connectionFactory.setPort(port);
        }
        return connectionFactory;
    }

}
