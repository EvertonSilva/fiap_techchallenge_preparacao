package br.com.lanchonete.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PedidoTest {
    private Pedido subject;

    @BeforeEach
    void setUp() {
        subject = new Pedido();
    }

    @Test
    void testPedidoDeveTerCodigo() {
        var codigoPedido = "9a6b3e72-5e18-4c2f-9d3b-065b6f34597a";
        subject = new Pedido(codigoPedido);
        assertThat(subject.getCodigo()).isEqualTo(codigoPedido);
    }

    @Test
    void testStatusInicial() {
        assertThat(subject.getStatus())
                .isEqualTo(Status.AGUARDANDO_PAGAMENTO);
    }

    @Test
    void testAvancaStatusParaRecebido() {
        var status = subject.getStatus();
        subject.avancaStatus();
        assertThat(status).isEqualTo(Status.AGUARDANDO_PAGAMENTO);
        assertThat(subject.getStatus()).isEqualTo(Status.RECEBIDO);
    }

    @Test
    void testAvancaStatusParaEmPreparacao() {
        subject.avancaStatus();
        var status = subject.getStatus();
        subject.avancaStatus();

        assertThat(status).isEqualTo(Status.RECEBIDO);
        assertThat(subject.getStatus()).isEqualTo(Status.EM_PREPARACAO);
    }

    @Test
    void testAvancaStatusParaPronto() {
        subject.avancaStatus();
        subject.avancaStatus();
        var status = subject.getStatus();
        subject.avancaStatus();
        
        assertThat(status).isEqualTo(Status.EM_PREPARACAO);
        assertThat(subject.getStatus()).isEqualTo(Status.PRONTO);
    }

    @Test
    void testAvancaStatusParaFinalizado() {
        subject.avancaStatus();
        subject.avancaStatus();
        subject.avancaStatus();
        var status = subject.getStatus();
        subject.avancaStatus();
        
        assertThat(status).isEqualTo(Status.PRONTO);
        assertThat(subject.getStatus()).isEqualTo(Status.FINALIZADO);
    }
}
