package br.com.lanchonete.domain;

import java.util.UUID;

public class Pedido {
    UUID id;
    Status status = Status.AGUARDANDO_PAGAMENTO;
}
