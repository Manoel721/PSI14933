package TestesUnitarios;

import Entidades.SimulacaoEntity;
import Resources.SimulacoesResource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SimulacoesResourceTest {

    private EntityManager emMock;
    private TypedQuery<SimulacaoEntity> queryMock;
    private SimulacoesResource resource;

    @BeforeEach
    void setUp() {
        emMock = Mockito.mock(EntityManager.class);
        queryMock = Mockito.mock(TypedQuery.class);

        resource = new SimulacoesResource();
        resource.em = emMock; // injetando o mock manualmente
    }

    @Test
    void deveRetornarListaDeSimulacoes() {
        // Arrange
        SimulacaoEntity simulacao1 = new SimulacaoEntity();
        SimulacaoEntity simulacao2 = new SimulacaoEntity();
        List<SimulacaoEntity> simulacoes = Arrays.asList(simulacao1, simulacao2);

        when(emMock.createQuery("FROM SimulacaoEntity", SimulacaoEntity.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(simulacoes);

        // Act
        List<SimulacaoEntity> resultado = resource.listarSimulacoes();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals(simulacao1, resultado.get(0));
        assertEquals(simulacao2, resultado.get(1));

        verify(emMock, times(1)).createQuery("FROM SimulacaoEntity", SimulacaoEntity.class);
        verify(queryMock, times(1)).getResultList();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHaSimulacoes() {
        // Arrange
        when(emMock.createQuery("FROM SimulacaoEntity", SimulacaoEntity.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(List.of());

        // Act
        List<SimulacaoEntity> resultado = resource.listarSimulacoes();

        // Assert
        assertEquals(0, resultado.size());
        verify(queryMock, times(1)).getResultList();
    }
}
