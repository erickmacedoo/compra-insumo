package feira.packages.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CompraInsumoRequest {

    @NotBlank(message = "A descrição da compra não pode estar em branco.")
    private String descricao;

    @NotNull(message = "O valor total é obrigatório.")
    @Positive(message = "O valor total deve ser um número positivo maior que zero.")
    private double valorTotal;

    @NotNull(message = "O ID do insumo é obrigatório.")
    private Long produtoId;

    @NotNull(message = "A categoria é obrigatória.")
    private Long categoriaId;

    public CompraInsumoRequest() {}

    public CompraInsumoRequest(String descricao, double valorTotal, Long produtoId, Long categoriaId) {
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.produtoId = produtoId;
        this.categoriaId = categoriaId;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    
    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
}