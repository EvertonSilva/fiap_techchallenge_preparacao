package br.com.lanchonete.producer;

import br.com.lanchonete.RedisDatabaseAdapter;
import br.com.lanchonete.infraestructure.IDatabaseAdapter;
import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;
import br.com.lanchonete.usecase.CadastraNovoPedidoUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class UseCaseProducer {

    @Produces
    public CadastraNovoPedidoUseCase cadastraNovoPedidoUseCaseProducer() {
        IDatabaseAdapter redisAdapter = new RedisDatabaseAdapter();
        return new CadastraNovoPedidoUseCase(redisAdapter);
    }

    @Produces
    public AtualizaStatusPedidoUseCase atualizaStatusPedidoUseCaseProducer() {
        return new AtualizaStatusPedidoUseCase();
    }
}
