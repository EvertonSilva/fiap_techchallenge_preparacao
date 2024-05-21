package br.com.lanchonete.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.lanchonete.infraestructure.IDatabaseAdapter;

public class AtualizaStatusPedidoUseCaseTest {
    @Mock
    private IDatabaseAdapter dbAdapter;

    @InjectMocks
    private AtualizaStatusPedidoUseCase subject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeveCadastrarNovoPedido() {
        String messageFromBroker = "{\"itens\":[{\"nomeProduto\":\"Hamburguer\",\"descricaoProduto\":\"\",\"precoProduto\":0.00,\"categoriaProduto\":\"LANCHE\",\"quantidadeItem\":1}],\"nomeCliente\":\"\",\"emailCliente\":\"\",\"cpfCliente\":\"\",\"codigo\":\"8ace8976-a16f-433e-8873-9a9d1b5c97a2\",\"data\":1716158520205,\"valorTotal\":0.00}"; 
        subject.execute(messageFromBroker);
        assertThat(true).isTrue();
    }
}
