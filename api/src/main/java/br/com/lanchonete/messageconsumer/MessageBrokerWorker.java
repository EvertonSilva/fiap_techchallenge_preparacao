package br.com.lanchonete.messageconsumer;

import br.com.lanchonete.rabbitmq.RabbitMqConsumer;
import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;
import br.com.lanchonete.usecase.CadastraNovoPedidoUseCase;
import br.com.lanchonete.usecase.IUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MessageBrokerWorker {
    private final AtualizaStatusPedidoUseCase atualizaStatusUseCase;
    private final CadastraNovoPedidoUseCase cadastraPedidoUseCase;
    private final RabbitMqConsumer consumer;
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBrokerWorker.class);

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
        LOGGER.info("Try get message from queue NOVO_PEDIDO");
        getMessageFrom("NOVO_PEDIDO", cadastraPedidoUseCase);
    }

    public void getMessagePagamentoEfetuado() {
        LOGGER.info("Try get message from queue PEDIDO_PAGO");
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
