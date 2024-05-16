package br.com.lanchonete.producer;

import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class AtualizaStatusPedidoUseCaseProducer {

    @Produces
    public AtualizaStatusPedidoUseCase atualizaStatusPedidoUseCase() {
        return new AtualizaStatusPedidoUseCase();
    }
}
