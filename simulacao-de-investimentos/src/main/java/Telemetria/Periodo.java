package Telemetria;

public class Periodo {
    private String inicio;
    private String fim;

    public Periodo(String inicio, String fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFim() {
        return fim;
    }
}
