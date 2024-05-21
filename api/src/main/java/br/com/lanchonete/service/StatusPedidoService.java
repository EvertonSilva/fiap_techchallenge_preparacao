package br.com.lanchonete.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.lanchonete.RedisDatabaseAdapter;
import br.com.lanchonete.domain.Pedido;
import br.com.lanchonete.infraestructure.IDatabaseAdapter;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatusPedidoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusPedidoService.class);
    private IDatabaseAdapter dbAdapter;
    
    public StatusPedidoService() {
        dbAdapter = new RedisDatabaseAdapter();
    }

    public Pedido getStatusPedido(String codigo) {
        var pedido = new Pedido();
        try {
            pedido = dbAdapter.findByCodigo(codigo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return pedido;
    }
}
