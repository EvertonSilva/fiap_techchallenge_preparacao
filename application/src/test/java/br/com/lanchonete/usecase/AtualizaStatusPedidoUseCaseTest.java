package br.com.lanchonete.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.lanchonete.infraestructure.IDatabaseAdapter;

class AtualizaStatusPedidoUseCaseTest {
    @Mock
    private IDatabaseAdapter dbAdapter;

    @InjectMocks
    private AtualizaStatusPedidoUseCase subject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
