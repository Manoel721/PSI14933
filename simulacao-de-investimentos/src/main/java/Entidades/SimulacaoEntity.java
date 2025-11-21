package Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Simulacao")
public class SimulacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    private String produto;
    private String tipoProduto;
    private Double valorInvestido;
    private Float rentabilidade;
    private Double valorFinal;
    private Integer prazoMeses;
    private String dataSimulacao;

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Double getValorInvestido() {
        return valorInvestido;
    }

    public String getProduto() {
        return produto;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public void setValorInvestido(Double valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public String getDataSimulacao() {
        return dataSimulacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public void setDataSimulacao(String dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }

    public void setTipo(String tipoProduto) {
    this.tipoProduto = tipoProduto;
    }

    public void setRentabilidade(Float rentabilidade) {
        this.rentabilidade = rentabilidade;
    }
    // getters e setters
}
