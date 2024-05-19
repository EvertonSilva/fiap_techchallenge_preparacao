package br.com.lanchonete.input;

import java.math.BigDecimal;
import java.util.Date;

public class PedidoMessageDTO {
    private String codigo;
    private String nomeCliente;
    private String emailCliente;
    private String cpfCliente;
    private Date data;
    private BigDecimal valorTotal;
}
