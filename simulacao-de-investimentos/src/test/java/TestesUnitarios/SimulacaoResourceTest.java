package TestesUnitarios;

import DTO.SimulacaoResponseDTO;
import Entidades.Investimento;
import Entidades.Simulacao;
import Entidades.SimulacaoEntity;
import Resources.SimulacaoResource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimulacaoResourceTest {

    private SimulacaoResource resource;
    private EntityManager em;
    private TypedQuery<Investimento> query;

    @BeforeEach
    void setUp() {
        em = mock(EntityManager.class);
        query = mock(TypedQuery.class);

        resource = new SimulacaoResource();
        resource.em = em;
    }

    @Test
    void deveRetornarBadRequestQuandoPayloadNulo() {
        Response response = resource.simularEGravar(null);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Envelope não enviado.", response.getEntity());
    }

    @Test
    void deveRetornarNotFoundQuandoProdutoNaoExiste() {
        Simulacao simulacao = new Simulacao();
        simulacao.setTipoProduto("CDB");

        when(em.createQuery(anyString(), eq(Investimento.class))).thenReturn(query);
        when(query.setParameter(eq("tipo"), any())).thenReturn(query);
        when(query.setMaxResults(1)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        Response response = resource.simularEGravar(simulacao);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertTrue(((String) response.getEntity()).contains("Produto não encontrado"));
    }

    @Test
    void deveRetornarCreatedQuandoSimulacaoValida() {
        // Preparar dados
        Simulacao simulacao = new Simulacao();
        simulacao.setClienteId(123L);
        simulacao.setTipoProduto("CDB");
        simulacao.setValor(1000.0);
        simulacao.setPrazoMeses(12);

        Investimento investimento = new Investimento();
        investimento.setId(1L);
        investimento.setNome("CDB Banco X");
        investimento.setTipo("CDB");
        investimento.setRentabilidade(0.01f);
        investimento.setRisco("Baixo");

        when(em.createQuery(anyString(), eq(Investimento.class))).thenReturn(query);
        when(query.setParameter(eq("tipo"), any())).thenReturn(query);
        when(query.setMaxResults(1)).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(investimento));

        // Executar
        Response response = resource.simularEGravar(simulacao);

        // Verificações
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity() instanceof SimulacaoResponseDTO);

        SimulacaoResponseDTO dto = (SimulacaoResponseDTO) response.getEntity();
        assertEquals("CDB Banco X", dto.getProdutoValidado().getNome());
        assertEquals(12, dto.getResultadoSimulacao().getPrazoMeses());
        assertNotNull(dto.getDataSimulacao());

        // Verificar persistência
        ArgumentCaptor<SimulacaoEntity> captor = ArgumentCaptor.forClass(SimulacaoEntity.class);
        verify(em).persist(captor.capture());
        SimulacaoEntity persisted = captor.getValue();
        assertEquals(123L, persisted.getClienteId());
        assertEquals("CDB Banco X", persisted.getProduto());
    }
}
