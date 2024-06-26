package br.com.lanchonete.rabbitmq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import com.rabbitmq.client.DeliverCallback;

// import jakarta.enterprise.context.ApplicationScoped;

// @ApplicationScoped
public class RabbitMqConsumer extends RabbitMqActor {

    public RabbitMqConsumer() throws IOException {
        super();
        channel.basicQos(0, 1, false);
    }

    public void receive(String queueName, Consumer<String> messageHandler) throws IOException {
        subscribe(queueName);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            messageHandler.accept(message);
            System.out.println(" [x] Received: " + message);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
