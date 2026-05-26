package feira.packages.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_compra_insumo")
public class CompraInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private double valorTotal;

    @Column(nullable = false, updatable = false)
    private LocalDate dataPedido;

    @Column
    private LocalDate dataRecebimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCompra status;

    @ManyToOne
    @JoinColumn(name = "insumo_id", nullable = false)
    private Insumo insumo;

    public CompraInsumo() {}

    public CompraInsumo(String descricao, double valorTotal, Insumo insumo) {
        this.setDescricao(descricao);
        this.setValorTotal(valorTotal);
        this.insumo = insumo;
        this.dataPedido = LocalDate.now();
        this.status = StatusCompra.SOLICITADO;
        this.dataRecebimento = null;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValorTotal() { return valorTotal; }
    public LocalDate getDataPedido() { return dataPedido; }
    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public StatusCompra getStatus() { return status; }
    public Insumo getInsumo() { return insumo; }

    public void setId(Long id) { this.id = id; }
    
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da CompraInsumo é obrigatória.");
        }
        if (descricao.trim().length() < 3) {
            throw new IllegalArgumentException("A descrição deve ter no mínimo 3 caracteres.");
        }
        this.descricao = descricao.trim();
    }

    public void setValorTotal(double valorTotal) {
        if (valorTotal <= 0) {
            throw new IllegalArgumentException("O valor total da compra deve ser maior que zero.");
        }
        this.valorTotal = valorTotal;
    }

    public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }
    public void setStatus(StatusCompra status) { this.status = status; }
    public void setInsumo(Insumo insumo) { this.insumo = insumo; }

}