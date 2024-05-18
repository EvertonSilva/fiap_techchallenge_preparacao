package br.com.lanchonete.usecase;

import java.util.UUID;

public class AtualizaStatusPedidoUseCase {

    public void execute(UUID pedido, String status) {
        // valida se pedido existe
        // valida mudança de status
        System.out.println("Atualizando pedido " + pedido + " para status " + status);
    }

    public void execute(String message) {
        // valida se pedido existe
        // valida mudança de status
    }
}
