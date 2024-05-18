package br.com.lanchonete.producer;

import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;
import br.com.lanchonete.rabbitmq.RabbitMqConsumer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MensageriaProducer {
    
    @Produces
    public RabbitMqConsumer rabbitMqConsumer() {
        return new RabbitMqConsumer();
    }

    @Produces
    public AtualizaStatusPedidoUseCase atualizaStatusPedidoUseCase() {
        return new AtualizaStatusPedidoUseCase();
    }
}
