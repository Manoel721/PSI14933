package Resources;

import DTO.InvestimentoDTO;
import Entidades.Simulacao;
import Repository.SimulacaoRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;

@Path("/")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvestimentoResource {
    @Inject
    SimulacaoRepository SR;

    @GET
    @Path("/investimentos/{clienteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarHistoricoInvestimento(@PathParam("clienteId") Long clienteId) {
        List<Simulacao> simulacoes = SR.listAll();

        // Verifica existência do cliente na base de simulações
        boolean clienteExiste = simulacoes.stream()
                .anyMatch(s -> Objects.equals(clienteId, s.getClienteId()));

        if (!clienteExiste) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"mensagem\":\"Cliente não encontrado.\"}")
                    .build();
        }

        if (simulacoes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"mensagem\":\"Produto não encontrado.\"}")
                    .build();
        }

        // Filtra apenas os investimentos do cliente informado
        List<InvestimentoDTO> investimentos = simulacoes.stream()
                .filter(i -> Objects.equals(clienteId, i.getClienteId()))
                .map(i -> new InvestimentoDTO(
                        i.getProdutoId(),
                        i.getTipoProduto(),   // supondo que o campo seja tipoProduto
                        i.getValorInvestido(),// ou outro campo que represente "valor"
                        i.getRentabilidade(),
                        i.getDataSimulacao()
                ))
                .toList();

        if (investimentos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"mensagem\":\"Nenhum investimento encontrado para o cliente informado.\"}")
                    .build();
        }

        return Response.ok(investimentos).build();
    }
}
