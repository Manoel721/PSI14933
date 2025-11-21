package Resources;

import Entidades.Investimento;
import Entidades.MotorRecomendacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutosRecomendadosResource {
    @Inject
    EntityManager em;

    @GET
    @Path("/produtos-recomendados/{perfil}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProdutosRecomendados(
            @PathParam("perfil") MotorRecomendacao.PerfilInvestidor perfil) throws JsonProcessingException {

        List<Investimento> resultados = em.createQuery(
                        "FROM Investimento", Investimento.class)
                .getResultList();

        if (resultados.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"mensagem\":\"Produto não encontrado.\"}")
                    .build();
        }

        if (perfil == MotorRecomendacao.PerfilInvestidor.MODERADO) {
            resultados = resultados.stream()
                    .filter(i -> "Medio".equalsIgnoreCase(i.getRisco()))
                    .toList();
        }
        else if (perfil == MotorRecomendacao.PerfilInvestidor.AGRESSIVO) {
            resultados = resultados.stream()
                    .filter(i -> "Alto".equalsIgnoreCase(i.getRisco()))
                    .toList();
        }
        else if (perfil == MotorRecomendacao.PerfilInvestidor.CONSERVADOR) {
            resultados = resultados.stream()
                    .filter(i -> "Baixo".equalsIgnoreCase(i.getRisco()))
                    .toList();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        String json = mapper.writeValueAsString(resultados);

        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
