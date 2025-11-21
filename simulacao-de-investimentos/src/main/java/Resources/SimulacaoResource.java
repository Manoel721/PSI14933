package Resources;

import DTO.ProdutoValidadoDTO;
import DTO.ResultadoSimulacaoDTO;
import DTO.SimulacaoResponseDTO;
import Entidades.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;//Para validar o payload
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.List;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import static Resources.TelemetriaResource.statsPostSimularInvestimento;

@Path("/")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimulacaoResource {

    @Inject
    public EntityManager em;

    @POST
    @Path("/simular-investimento")
    @Transactional
    public Response simularEGravar(@Valid Simulacao simulacao) {
        long inicio = System.nanoTime();
        //Validação do payload:
        //Se a validação falhar, o Quarkus irá retornar automaticamnte 400 Bad Request
        if (simulacao == null) { // Para payload do JSON não enviado
            return Response.status(Response.Status.BAD_REQUEST).entity("Envelope não enviado.").build();
        }
        // Buscar o produto pelo tipo informado
        List<Investimento> resultados = em.createQuery(
                        "FROM Investimento i WHERE i.tipo = :tipo", Investimento.class)
                .setParameter("tipo", simulacao.getTipoProduto())
                .setMaxResults(1)
                .getResultList();

        if (resultados.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Produto não encontrado para o tipo: " + simulacao.getTipoProduto())
                    .build();
        }

        Investimento produto = resultados.get(0);

        // Montar DTO do produto validado
        ProdutoValidadoDTO produtoDTO = new ProdutoValidadoDTO();
        //produtoDTO.setClienteId(simulacao.getClienteId());
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setTipo(produto.getTipo());
        produtoDTO.setRentabilidade(produto.getRentabilidade());
        produtoDTO.setRisco(produto.getRisco());

        // Calcular resultado da simulação
        ResultadoSimulacaoDTO resultadoDTO = new ResultadoSimulacaoDTO();
        resultadoDTO.setValorFinal(calculaDados(
                simulacao.getValor(),
                simulacao.getPrazoMeses(),
                produto.getRentabilidade()
        ));
        resultadoDTO.setRentabilidadeEfetiva(produto.getRentabilidade() * simulacao.getPrazoMeses());
        resultadoDTO.setPrazoMeses(simulacao.getPrazoMeses());

        // Montar responseDTO
        SimulacaoResponseDTO responseDTO = new SimulacaoResponseDTO();
        responseDTO.setProdutoValidado(produtoDTO);
        responseDTO.setResultadoSimulacao(resultadoDTO);
        responseDTO.setDataSimulacao(Instant.now().toString());

        // Persistir entidade no banco
        SimulacaoEntity simulacaoEntity = new SimulacaoEntity();
        simulacaoEntity.setClienteId(simulacao.getClienteId());
        simulacaoEntity.setTipo(produto.getTipo());
        simulacaoEntity.setProduto(produto.getNome());
        simulacaoEntity.setRentabilidade(produto.getRentabilidade());
        simulacaoEntity.setValorInvestido(simulacao.getValor());
        simulacaoEntity.setValorFinal(resultadoDTO.getValorFinal());
        simulacaoEntity.setPrazoMeses(resultadoDTO.getPrazoMeses());
        simulacaoEntity.setDataSimulacao(responseDTO.getDataSimulacao());

        em.persist(simulacaoEntity);

        long fim = System.nanoTime();
        long tempoExecucaoMs = (fim - inicio) / 1_000_000;

        statsPostSimularInvestimento.registrarExecucao(tempoExecucaoMs);

         // Retornar DTO para o cliente
        return Response.status(Response.Status.CREATED)
                .entity(responseDTO)
                .build();
    }
    private Double calculaDados(Double valor, Integer prazoMeses, Float rentabilidade) {
        return (Double) Math.ceil(valor + (valor * prazoMeses * rentabilidade));
    }
    /*private LocalDateTime parseData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(data, formatter);
    }*/
}
