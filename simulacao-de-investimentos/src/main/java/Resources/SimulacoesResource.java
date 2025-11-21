package Resources;

import Entidades.SimulacaoEntity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimulacoesResource {
    @Inject
    public EntityManager em;

    @GET
    @Path("/simulacoes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SimulacaoEntity> listarSimulacoes() {
        return em.createQuery("FROM SimulacaoEntity", SimulacaoEntity.class)
                .getResultList();
    }

}
