package br.com.lanchonete.input;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record PedidoMessageDTO(
    String codigo,
    String nomeCliente,
    String emailCliente,
    String cpfCliente,
    Date data,
    BigDecimal valorTotal,
    List<ItemPedidoDTO> itens
) {}
