package Resources;

import Entidades.SimulacaoEntity;
import Repository.SPPDRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimulacoesProdutoDiaResource {
    @Inject
    SPPDRepository SPPDR;

    @GET
    @Path("/simulacoes/por-produto-dia")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarPorProdutoDia() throws JsonProcessingException {

        List<SimulacaoEntity> listaDatas = SPPDR.listAll();
        Set<String> dattas = new HashSet<>(Set.of());
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode resultado = mapper.createArrayNode();

        String jsonSaida = null;
        for (SimulacaoEntity s1 : listaDatas) {
            dattas.add(s1.getDataSimulacao().substring(0, 10));
        }
        List<SimulacaoEntity> simulacoes = SPPDR.listAll();
        for (String data : dattas) {
            // Agrupamento por produto
            Map<String, List<SimulacaoEntity>> agrupado = simulacoes.stream()
                    .collect(Collectors.groupingBy(SimulacaoEntity::getProduto));

            for (Map.Entry<String, List<SimulacaoEntity>> entry : agrupado.entrySet()) {
                String produto = entry.getKey();
                List<SimulacaoEntity> lista = entry.getValue();

                int quantidade = lista.size();
                double mediaValorFinal = lista.stream().mapToDouble(SimulacaoEntity::getValorFinal).average().orElse(0);

                ObjectNode obj = mapper.createObjectNode();
                obj.put("produto", produto);
                obj.put("data", data);
                obj.put("quantidadeSimulacoes", quantidade);
                obj.put("mediaValorFinal", Math.round(mediaValorFinal * 100.0) / 100.0);

                resultado.add(obj);
            }

        }
        // Saída JSON
        jsonSaida = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultado);
        System.out.println(jsonSaida);

        return jsonSaida;
    }
}
