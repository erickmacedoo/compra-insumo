package feira.packages.dto;

import feira.packages.domain.CompraInsumo;
import java.time.LocalDate;

public class CompraInsumoResponse {
    
    private Long id;
    private String descricao;
    private double valorTotal;
    private LocalDate dataPedido;
    private LocalDate dataRecebimento;
    private String status;
    private String nomeProduto;

    public CompraInsumoResponse() {}

    public CompraInsumoResponse(CompraInsumo compra) {
        this.id = compra.getId();
        this.descricao = compra.getDescricao();
        this.valorTotal = compra.getValorTotal();
        this.dataPedido = compra.getDataPedido();
        this.dataRecebimento = compra.getDataRecebimento();
        this.status = compra.getStatus().name();
        this.nomeProduto = compra.getProduto().getNome();
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValorTotal() { return valorTotal; }
    public LocalDate getDataPedido() { return dataPedido; }
    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public String getStatus() { return status; }
    public String getNomeProduto() { return nomeProduto; }
}
