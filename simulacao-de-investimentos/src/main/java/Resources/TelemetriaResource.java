package Resources;

import Entidades.SimulacaoEntity;
import Entidades.TelemetriaStats;
import Repository.SPPDRepository;
import Telemetria.Periodo;
import Telemetria.Servico;
import Telemetria.TelemetriaResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Path("/telemetria")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelemetriaResource {
    @Inject
    SPPDRepository SPPDR;

    private static final List<String> dadosTelemetria = new ArrayList<>();

    // Estatísticas por endpoint
    public static final TelemetriaStats statsPostSimularInvestimento = new TelemetriaStats();
    public static final TelemetriaStats statsGetPerfilRiscoClienteId = new TelemetriaStats();

    @GET
    public Response obterTelemetria() {
        List<SimulacaoEntity> listaDatas = SPPDR.listAll();
        Set<String> dattas = new HashSet<>(Set.of());

        String jsonSaida = null;
        for (SimulacaoEntity s1 : listaDatas) {
            dattas.add(s1.getDataSimulacao().substring(0, 10));
        }
        // Converter para LocalDate
        List<LocalDate> datasConvertidas = dattas.stream()
                .map(LocalDate::parse)
                .collect(Collectors.toList());

        // Encontrar a mais antiga e a mais atual
        LocalDate maisAntiga = Collections.min(datasConvertidas);
        LocalDate maisAtual = Collections.max(datasConvertidas);

        List<Servico> servicos = List.of(
                new Servico("POST /simular-investimento",
                        statsPostSimularInvestimento.getChamadas(),
                        statsPostSimularInvestimento.getMediaTempoExecucaoMs()),
                new Servico("GET /perfil-risco/{clienteId}",
                        statsGetPerfilRiscoClienteId.getChamadas(),
                        statsGetPerfilRiscoClienteId.getMediaTempoExecucaoMs())
        );

        Periodo periodo = new Periodo("" + maisAntiga, "" + maisAtual);

        return Response.ok(new TelemetriaResponse(servicos, periodo)).build();
    }

}
