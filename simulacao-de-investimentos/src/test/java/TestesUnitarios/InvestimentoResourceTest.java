package TestesUnitarios;

import DTO.InvestimentoDTO;
import Entidades.Simulacao;
import Repository.SimulacaoRepository;
import Resources.InvestimentoResource;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InvestimentoResourceTest {

    @Mock
    private SimulacaoRepository simulacaoRepository;

    @InjectMocks
    private InvestimentoResource investimentoResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarNotFoundQuandoNaoExistemSimulacoes() {
        when(simulacaoRepository.listAll()).thenReturn(List.of());

        Response response = investimentoResource.listarHistoricoInvestimento(1L);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals("{\"mensagem\":\"Cliente não encontrado.\"}", response.getEntity());
    }

    @Test
    void deveRetornarNotFoundQuandoClienteNaoPossuiInvestimentos() {
        Simulacao simulacao = new Simulacao();
        simulacao.setClienteId(2L); // cliente diferente
        simulacao.setProdutoId(10L);

        when(simulacaoRepository.listAll()).thenReturn(List.of(simulacao));

        Response response = investimentoResource.listarHistoricoInvestimento(1L);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals("{\"mensagem\":\"Cliente não encontrado.\"}", response.getEntity());
    }

    @Test
    void deveRetornarInvestimentosQuandoClientePossuiSimulacoes() {
        Simulacao simulacao = new Simulacao();
        simulacao.setClienteId(1L);
        simulacao.setProdutoId(10L);
        simulacao.setTipoProduto("CDB");
        simulacao.setValorInvestido(1000.0);
        simulacao.setRentabilidade(0.12F);
        simulacao.setDataSimulacao(String.valueOf(LocalDate.now()));

        when(simulacaoRepository.listAll()).thenReturn(List.of(simulacao));

        Response response = investimentoResource.listarHistoricoInvestimento(1L);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        @SuppressWarnings("unchecked")
        List<InvestimentoDTO> investimentos = (List<InvestimentoDTO>) response.getEntity();

        assertEquals(1, investimentos.size());
        assertEquals(10L, investimentos.get(0).produtoId());
        assertEquals("CDB", investimentos.get(0).tipoProduto());
        assertEquals(1000.0, investimentos.get(0).valorInvestido());
        assertEquals(0.12F, investimentos.get(0).rentabilidade());
    }
}

