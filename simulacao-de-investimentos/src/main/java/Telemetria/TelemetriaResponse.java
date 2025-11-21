package Telemetria;

import java.util.*;

public class TelemetriaResponse {
    private List<Servico> servicos;
    private Periodo periodo;

    public TelemetriaResponse(List<Servico> servicos, Periodo periodo) {
        this.servicos = servicos;
        this.periodo = periodo;
    }

    public List<Servico> getServicos() { return servicos; }
    public Periodo getPeriodo() { return periodo; }
}

