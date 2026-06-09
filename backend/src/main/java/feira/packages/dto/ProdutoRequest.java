package feira.packages.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequest {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotNull(message = "A categoria é obrigatória.")
    private Long categoriaId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}