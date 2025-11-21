package TestesUnitarios;

import Repository.SimulacaoRepository;
import Resources.PerfilRiscoResource;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PerfilRiscoResourceTest {

    @Mock
    private SimulacaoRepository simulacaoRepo;

    @InjectMocks
    private PerfilRiscoResource resource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarNotFoundQuandoNaoExistemSimulacoesDoCliente() {
        when(simulacaoRepo.listAll()).thenReturn(Collections.emptyList());

        Response response = resource.recomendar(1L);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity().toString().contains("Nenhuma simulação encontrada"));
    }

}
