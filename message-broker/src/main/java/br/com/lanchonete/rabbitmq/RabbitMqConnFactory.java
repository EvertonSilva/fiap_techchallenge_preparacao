package br.com.lanchonete.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqConnFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConnFactory.class);
    private static Connection connection;


    public static Connection getConnection() {
        if (connection == null || !connection.isOpen()) {
            try {
                var factory = new ConnectionFactory();
                factory.setHost("localhost");
                factory.setUsername("fiap");
                factory.setPassword("Rabbit!1@2#3");
                connection = factory.newConnection();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return connection;
    }
}
