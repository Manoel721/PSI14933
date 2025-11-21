package TestesUnitarios;

import Entidades.SimulacaoEntity;
import Repository.SPPDRepository;
import Resources.SimulacoesProdutoDiaResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimulacoesProdutoDiaResourceTest {

    @Mock
    private SPPDRepository sppdRepository;

    @InjectMocks
    private SimulacoesProdutoDiaResource resource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private SimulacaoEntity criarSimulacao(Long id, String produto, String data, double valorFinal) {
        SimulacaoEntity entity = new SimulacaoEntity();
        entity.setId(id);
        entity.setProduto(produto);
        entity.setDataSimulacao(data);
        entity.setValorFinal(valorFinal);
        return entity;
    }

    @Test
    void deveRetornarJsonComAgrupamentoPorProdutoEDia() throws JsonProcessingException {
        SimulacaoEntity s1 = criarSimulacao(1L, "CDB", "2025-11-18T10:00:00", 1200.0);
        SimulacaoEntity s2 = criarSimulacao(2L, "CDB", "2025-11-18T11:00:00", 1300.0);
        SimulacaoEntity s3 = criarSimulacao(3L, "LCI", "2025-11-18T12:00:00", 2000.0);

        when(sppdRepository.listAll()).thenReturn(List.of(s1, s2, s3));

        String json = resource.listarPorProdutoDia();

        assertNotNull(json);
        assertTrue(json.contains("CDB"));
        assertTrue(json.contains("LCI"));
        assertTrue(json.contains("2025-11-18"));
        assertTrue(json.contains("quantidadeSimulacoes"));
        assertTrue(json.contains("mediaValorFinal"));
    }

    @Test
    void deveRetornarJsonVazioQuandoNaoExistemSimulacoes() throws JsonProcessingException {
        when(sppdRepository.listAll()).thenReturn(List.of());

        String json = resource.listarPorProdutoDia();

        assertNotNull(json);
        assertEquals("[ ]", json.trim());
    }

    @Test
    void deveCalcularMediaCorretamente() throws JsonProcessingException {
        SimulacaoEntity s1 = criarSimulacao(1L, "CDB", "2025-11-18T10:00:00", 1000.0);
        SimulacaoEntity s2 = criarSimulacao(2L, "CDB", "2025-11-18T11:00:00", 2000.0);

        when(sppdRepository.listAll()).thenReturn(List.of(s1, s2));

        String json = resource.listarPorProdutoDia();

        // Média esperada = (1000 + 2000) / 2 = 1500.0
        assertTrue(json.contains("\"mediaValorFinal\" : 1500.0"));
    }
}
