package br.com.lanchonete.messageconsumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.lanchonete.rabbitmq.RabbitMqConsumer;
import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;
import br.com.lanchonete.usecase.CadastraNovoPedidoUseCase;
import br.com.lanchonete.usecase.IUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Worker {
    private static final Logger LOGGER = LoggerFactory.getLogger(Worker.class);
    @Inject
    private CadastraNovoPedidoUseCase cadastraPedidoUseCase;
    @Inject
    private AtualizaStatusPedidoUseCase atualizaStatusUseCase;
    @Inject
    private RabbitMqConsumer consumer;

    public void init() {
        LOGGER.info("MAS QUE CARALAHA!!!!");
    }

    public void getMessageNovoPedido() {
        proccessMessageFrom("NOVO_PEDIDO", cadastraPedidoUseCase);
    }

    public void getMessagePagamentoEfetuado() {
        proccessMessageFrom("PEDIDO_PAGO", atualizaStatusUseCase);
    }

    private void proccessMessageFrom(String queueName, IUseCase useCase) {
        try {
            LOGGER.info("Processando mensagens da fila {}", queueName);
            consumer.receive(queueName, useCase::execute);
        } catch (IOException e) {
            LOGGER.error("Erro ao processar fila {}\nDetalhes: {}", queueName, e.getMessage());
        }
    }
}
