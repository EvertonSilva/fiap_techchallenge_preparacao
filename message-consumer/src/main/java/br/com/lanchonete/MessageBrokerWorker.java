package br.com.lanchonete;


import br.com.lanchonete.rabbitmq.RabbitMqConsumer;
import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;
import br.com.lanchonete.usecase.CadastraNovoPedidoUseCase;
import br.com.lanchonete.usecase.IUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;

@ApplicationScoped
public class MessageBrokerWorker {
    private final AtualizaStatusPedidoUseCase atualizaStatusUseCase;
    private final CadastraNovoPedidoUseCase cadastraPedidoUseCase;
    private final RabbitMqConsumer consumer;

    @Inject
    public MessageBrokerWorker(
            AtualizaStatusPedidoUseCase atualizaStatusUseCase,
            CadastraNovoPedidoUseCase cadastraPedidoUseCase,
            RabbitMqConsumer consumer) {
        this.atualizaStatusUseCase = atualizaStatusUseCase;
        this.cadastraPedidoUseCase = cadastraPedidoUseCase;
        this.consumer = consumer;
    }

    public void getMessageNovoPedido() {
        getMessageFrom("NOVO_PEDIDO", cadastraPedidoUseCase);
    }

    public void getMessagePagamentoEfetuado() {
        getMessageFrom("PEDIDO_PAGO", atualizaStatusUseCase);
    }

    private void getMessageFrom(String queueName, IUseCase useCase) {
        try {
            consumer.receive(queueName, useCase::execute);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
