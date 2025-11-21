package Entidades;

import java.util.function.Function;

public class MotorRecomendacao {

    public enum PerfilInvestidor {
        CONSERVADOR,
        MODERADO,
        AGRESSIVO
    }

    private Long clienteIdd;
    private PerfilInvestidor perfil;
    private int pontuacao;
    private String descricao;

    public MotorRecomendacao (Long clienteId, double volumeInvestimentos,
                                                    int frequenciaMovimentacoes,
                                                    String preferencia) {
        clienteIdd = clienteId;
        pontuacao = 0;

        // Volume de investimentos
        if (volumeInvestimentos < 10000) {
            pontuacao += 10; // baixo volume
        } else if (volumeInvestimentos < 50000) {
            pontuacao += 20; // médio volume
        } else {
            pontuacao += 30; // alto volume
        }

        // Frequência de movimentações
        if (frequenciaMovimentacoes < 5) {
            pontuacao += 10; // baixa movimentação
        } else if (frequenciaMovimentacoes < 15) {
            pontuacao += 20; // média movimentação
        } else {
            pontuacao += 30; // alta movimentação
        }

        // Preferência por liquidez ou rentabilidade
        if ("liquidez".equalsIgnoreCase(preferencia)) {
            pontuacao += 10;
        } else if ("equilibrio".equalsIgnoreCase(preferencia)) {
            pontuacao += 20;
        } else if ("rentabilidade".equalsIgnoreCase(preferencia)) {
            pontuacao += 30;
        }

        // Definir perfil com base na pontuação
        if (pontuacao <= 40) {
            descricao = "Perfil que não arrisca muito.";
            perfil =  PerfilInvestidor.CONSERVADOR;
        } else if (pontuacao <= 70) {
            descricao = "Perfil equilibrado entre segurança e rentabilidade.";
            perfil =  PerfilInvestidor.MODERADO;
        } else {
            descricao = "Perfil que aceita grandes riscos em busca de rentabilidade.";
            perfil =  PerfilInvestidor.AGRESSIVO;
        }

    }

    public Long getClienteIdd() {
        return clienteIdd;
    }

    public String getDescricao() {
        return descricao;
    }

    public PerfilInvestidor getPerfil() {
        return perfil;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setClienteIdd(Long clienteIdd) {
        this.clienteIdd = clienteIdd;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void setPerfil(PerfilInvestidor perfil) {
        this.perfil = perfil;
    }
}
