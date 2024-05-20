package br.com.lanchonete.producer;

import java.io.IOException;

import br.com.lanchonete.rabbitmq.RabbitMqConsumer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MessageBrokerProducer {

    @Produces
    public RabbitMqConsumer rabbitMqConsumer() throws IOException {
        return new RabbitMqConsumer();
    }

}
