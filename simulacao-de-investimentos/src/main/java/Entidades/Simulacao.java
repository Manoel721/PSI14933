package Entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Simulacao extends PanacheEntity {
    private Long clienteId;
    @Column(name = "valorInvestido")
    private Double valor;
    private Integer prazoMeses;
    @Column(name = "tipoProduto")
    private String tipoProduto;
    @Column(name = "rentabilidade")
    private Float rentabilidade;
    private String dataSimulacao;
    private Long id;

    public Long getClienteId() {
        return clienteId;
    }

    public Long getProdutoId() {
        return id;
    }


    public String getDataSimulacao() {
        return dataSimulacao;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public Long clienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setProdutoId(Long produtoId) {
        this.id = produtoId;
    }
    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public void setDataSimulacao(String dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRentabilidade(Float rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public void setValorInvestido(Double valor) {
        this.valor = valor;
    }

    public Double getValorInvestido() {
        return valor;
    }

    public Float getRentabilidade() {
        return rentabilidade;
    }

}
