package br.com.lanchonete.infraestructure;

import br.com.lanchonete.domain.Pedido;

public interface IDatabaseAdapter {
    void save(Pedido pedido);
    Pedido findByCodigo(String codigo);
}
