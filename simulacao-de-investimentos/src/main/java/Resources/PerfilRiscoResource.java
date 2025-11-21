package Resources;

import Entidades.MotorRecomendacao;
import Entidades.Simulacao;
import Repository.SimulacaoRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static Resources.TelemetriaResource.statsGetPerfilRiscoClienteId;

@Path("/")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilRiscoResource {

    @Inject
    EntityManager em;

    @Inject
    SimulacaoRepository simulacaoRepo;

    // DTO de saída
    public static class RecomendacaoResponse {
        public Long clienteId;
        public String perfil;
        public int pontuacao;
        public String descricao;

        public RecomendacaoResponse(Long clienteId, String perfil, int pontuacao, String descricao) {
            this.clienteId = clienteId;
            this.perfil = perfil;
            this.pontuacao = pontuacao;
            this.descricao = descricao;
        }
    }

    @GET
    @Path("/perfil-risco/{clienteId}")
    public Response recomendar(@PathParam("clienteId") Long clienteId) {
        long inicio = System.nanoTime();
        // Buscar todas as simulações
        List<Simulacao> simulacoes = simulacaoRepo.listAll();

        // Filtrar apenas as simulações do cliente
        List<Simulacao> simulacoesCliente = simulacoes.stream()
                .filter(s -> s.getClienteId().equals(clienteId))
                .toList();

        // Teste: verificar se clienteId existe
        if (simulacoesCliente.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma simulação encontrada para o clienteId: " + clienteId)
                    .build();
        }

        double volumeInvestimentos = simulacoesCliente.stream()
                .mapToDouble(Simulacao::getValorInvestido)
                .sum();

        int frequenciaMovimentacoes = simulacoesCliente.size();

        double mediaPrazoMeses = simulacoesCliente.stream()
                .mapToInt(Simulacao::getPrazoMeses)
                .average()
                .orElse(0.0);

        // Definir preferência com base no prazo médio
        String preferencia;
        if (mediaPrazoMeses >= 60) {
            preferencia = "liquidez";
        } else if (mediaPrazoMeses < 13) {
            preferencia = "rentabilidade";
        } else {
            preferencia = "equilibrio";
        }

        // Instanciar o motor de recomendação
        MotorRecomendacao motor = new MotorRecomendacao(
                clienteId,
                volumeInvestimentos,
                frequenciaMovimentacoes,
                preferencia
        );

        // Montar resposta
        RecomendacaoResponse response = new RecomendacaoResponse(
                motor.getClienteIdd(),
                motor.getPerfil().name(),
                motor.getPontuacao(),
                motor.getDescricao()
        );
        long fim = System.nanoTime();
        long tempoExecucaoMs = (fim - inicio) / 1_000_000;

        statsGetPerfilRiscoClienteId.registrarExecucao(tempoExecucaoMs);
        return Response.ok(response).build();
    }

}
