package br.com.lanchonete.input;

import java.math.BigDecimal;

public record ItemPedidoDTO (
    String nomeProduto,
    String descricaoProduto,
    BigDecimal precoProduto,
    String categoriaProduto,
    Integer quantidadeItem
) {}