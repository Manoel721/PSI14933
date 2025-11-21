package Telemetria;

public class Servico {
    private String nome;
    private int quantidadeChamadas;
    private double mediaTempoRespostaMs;

    public Servico(String nome, int quantidadeChamadas, double mediaTempoRespostaMs) {
        this.nome = nome;
        this.quantidadeChamadas = quantidadeChamadas;
        this.mediaTempoRespostaMs = mediaTempoRespostaMs;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeChamadas() {
        return quantidadeChamadas;
    }

    public double getMediaTempoRespostaMs() {
        return mediaTempoRespostaMs;
    }
}
