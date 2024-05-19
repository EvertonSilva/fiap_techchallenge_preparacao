package br.com.lanchonete;


import br.com.lanchonete.rabbitmq.RabbitMqConsumer;
import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;

@ApplicationScoped
public class MessageBrokerWorker {
    private final AtualizaStatusPedidoUseCase useCase;
    private final RabbitMqConsumer consumer;

    @Inject
    public MessageBrokerWorker(AtualizaStatusPedidoUseCase useCase, RabbitMqConsumer consumer) {
        this.useCase = useCase;
        this.consumer = consumer;
    }

    public void getMessageNovoPedido() {
        getMessageFrom("NOVO_PEDIDO");
    }

    public void getMessagePagamentoEfetuado() {
        getMessageFrom("PEDIDO_PAGO");
    }

    private void getMessageFrom(String queueName) {
        try {
            consumer.receive(queueName, useCase::execute);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
