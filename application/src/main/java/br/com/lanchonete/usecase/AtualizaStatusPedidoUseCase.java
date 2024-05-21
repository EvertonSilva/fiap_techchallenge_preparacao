package br.com.lanchonete.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lanchonete.domain.Pedido;
import br.com.lanchonete.infraestructure.IDatabaseAdapter;
import br.com.lanchonete.input.PedidoMessageDTO;

public class AtualizaStatusPedidoUseCase implements IUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtualizaStatusPedidoUseCase.class);
    private final IDatabaseAdapter dbAdapter;

    public AtualizaStatusPedidoUseCase(IDatabaseAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    public void execute(String idPedido, String status) {
        var pedido = dbAdapter.findByCodigo(idPedido);
        if (pedido == null) {
            LOGGER.warn("Pedido com código {}, não encontardo.", idPedido);
            return;
        }
        pedido.setStatus(status);
        dbAdapter.save(pedido);
    }

    public void execute(String messageFromBroker) {
        try {
            var mapper = new ObjectMapper();
            var dto = mapper.readValue(messageFromBroker, PedidoMessageDTO.class);
            var pedido = new Pedido(dto.codigo());
            pedido.avancaStatus();
            dbAdapter.save(pedido);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
