package br.com.lanchonete.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lanchonete.domain.Pedido;
import br.com.lanchonete.infraestructure.IDatabaseAdapter;
import br.com.lanchonete.input.PedidoMessageDTO;

public class CadastraNovoPedidoUseCase implements IUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CadastraNovoPedidoUseCase.class);
    private final IDatabaseAdapter dbAdapter;

    public CadastraNovoPedidoUseCase(IDatabaseAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    @Override
    public void execute(String messageFromBroker) {
        try {
            var mapper = new ObjectMapper();
            var dto = mapper.readValue(messageFromBroker, PedidoMessageDTO.class);
            var pedido = new Pedido(dto.codigo());
            dbAdapter.save(pedido);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
