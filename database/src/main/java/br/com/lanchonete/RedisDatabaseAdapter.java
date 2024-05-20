package br.com.lanchonete;

import br.com.lanchonete.domain.Pedido;
import br.com.lanchonete.infraestructure.IDatabaseAdapter;

public class RedisDatabaseAdapter implements IDatabaseAdapter {

    @Override
    public void save(Pedido pedido) {
        System.out.println("Salvando pedido...");
    }

    @Override
    public Pedido findByCodigo(String codigo) {
        System.out.println("Lendo pedido...");
        return new Pedido();
    }

}
