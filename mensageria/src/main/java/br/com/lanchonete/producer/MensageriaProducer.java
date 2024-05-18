package br.com.lanchonete.producer;

import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;

import br.com.lanchonete.rabbitmq.RabbitMqConsumer;
import java.io.IOException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MensageriaProducer {
    
    @Produces
    public RabbitMqConsumer rabbitMqConsumer() throws IOException {
        return new RabbitMqConsumer();
    }

    @Produces
    public AtualizaStatusPedidoUseCase atualizaStatusPedidoUseCase() {
        return new AtualizaStatusPedidoUseCase();
    }
}
