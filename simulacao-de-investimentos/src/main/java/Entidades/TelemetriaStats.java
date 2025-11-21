package Entidades;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TelemetriaStats {
    private final AtomicInteger chamadas = new AtomicInteger(0);
    private final AtomicLong tempoTotalMs = new AtomicLong(0);

    public void registrarExecucao(long tempoExecucaoMs) {
        chamadas.incrementAndGet();
        tempoTotalMs.addAndGet(tempoExecucaoMs);
    }

    public int getChamadas() {
        return chamadas.get();
    }

    public double getMediaTempoExecucaoMs() {
        int qtd = chamadas.get();
        return (qtd == 0) ? 0 : (double) tempoTotalMs.get() / qtd;
    }
}
