package br.com.lanchonete.usecase;

import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lanchonete.domain.Pedido;
import br.com.lanchonete.input.PedidoMessageDTO;

public class AtualizaStatusPedidoUseCase implements IUseCase {

    public void execute(UUID pedido, String status) {
        System.out.println("Atualizando pedido " + pedido + " para status " + status);
    }

    public void execute(String messageFromBroker) {
        try {
            var mapper = new ObjectMapper();
            var dto = mapper.readValue(messageFromBroker, PedidoMessageDTO.class);
            var pedido = new Pedido(dto.codigo());
            pedido.avancaStatus();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
