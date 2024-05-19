package br.com.lanchonete.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PedidoTest {
    private Pedido subject;

    @BeforeEach
    void setUp() {
        subject = new Pedido();
    }

    @Test
    public void testPedidoDeveTerCodigo() {
        var codigoPedido = "9a6b3e72-5e18-4c2f-9d3b-065b6f34597a";
        subject = new Pedido(codigoPedido);
        assertThat(subject.getCodigo()).isEqualTo(codigoPedido);
    }

    @Test
    public void testStatusInicial() {
        assertThat(subject.getStatus())
                .isEqualTo(Status.AGUARDANDO_PAGAMENTO);
    }

    @Test
    public void testAvancaStatusParaRecebido() {
        var status = subject.getStatus();
        subject.avancaStatus();
        assertThat(status).isEqualTo(Status.AGUARDANDO_PAGAMENTO);
        assertThat(subject.getStatus()).isEqualTo(Status.RECEBIDO);
    }

    @Test
    public void testAvancaStatusParaEmPreparacao() {
        subject.avancaStatus();
        var status = subject.getStatus();
        subject.avancaStatus();

        assertThat(status).isEqualTo(Status.RECEBIDO);
        assertThat(subject.getStatus()).isEqualTo(Status.EM_PREPARACAO);
    }

    @Test
    public void testAvancaStatusParaPronto() {
        subject.avancaStatus();
        subject.avancaStatus();
        var status = subject.getStatus();
        subject.avancaStatus();
        
        assertThat(status).isEqualTo(Status.EM_PREPARACAO);
        assertThat(subject.getStatus()).isEqualTo(Status.PRONTO);
    }

    @Test
    public void testAvancaStatusParaFinalizado() {
        subject.avancaStatus();
        subject.avancaStatus();
        subject.avancaStatus();
        var status = subject.getStatus();
        subject.avancaStatus();
        
        assertThat(status).isEqualTo(Status.PRONTO);
        assertThat(subject.getStatus()).isEqualTo(Status.FINALIZADO);
    }
}
