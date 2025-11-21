package TestesUnitarios;

import Entidades.Investimento;
import Entidades.MotorRecomendacao;
import Resources.ProdutosRecomendadosResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutosRecomendadosResourceTest {

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Investimento> query;

    @InjectMocks
    private ProdutosRecomendadosResource resource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(em.createQuery("FROM Investimento", Investimento.class)).thenReturn(query);
    }

    @Test
    void deveRetornarNotFoundQuandoNaoExistemInvestimentos() throws JsonProcessingException {
        when(query.getResultList()).thenReturn(Collections.emptyList());

        Response response = resource.listarProdutosRecomendados(MotorRecomendacao.PerfilInvestidor.MODERADO);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity().toString().contains("Produto não encontrado"));
    }

}
