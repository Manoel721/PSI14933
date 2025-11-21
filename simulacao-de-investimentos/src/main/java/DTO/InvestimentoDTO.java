package DTO;

public class InvestimentoDTO {
    private Long id;
    private String tipo;
    private Double valor;
    private Float rentabilidade;
    private String data;

    public InvestimentoDTO(Long id, String tipo, Double valor, Float rentabilidade, String data) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.rentabilidade = rentabilidade;
        this.data = data;
    }

    // getters e setters

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getValor() {
        return valor;
    }

    public Float rentabilidade() {
        return rentabilidade;
    }

    public String getData() {
        return data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setRentabilidade(Float rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long produtoId() {
        return id;
    }

    public String tipoProduto() {
        return tipo;
    }

    public double valorInvestido() {
        return valor;
    }

}
