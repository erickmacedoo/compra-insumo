import { Component, OnInit, Output, EventEmitter, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProdutoService } from '../../services/produto.service';

@Component({
  selector: 'app-lista-produtos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './lista-produtos.html',
  styleUrls: ['./lista-produtos.css']
})
export class ListaProdutos implements OnInit {
  @Output() editarNoForm = new EventEmitter<any>();
  produtos: any[] = [];

  constructor(private produtoService: ProdutoService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.produtoService.listarTodos().subscribe({
      next: (dados) => {
        this.produtos = dados;
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Erro ao buscar produtos', err)
    });
  }

  repassarProduto(produtoClicado: any): void {
    this.editarNoForm.emit(produtoClicado);
  }

  remover(id: number): void {
    if (confirm('Tem certeza que deseja remover este produto?')) {
      this.produtoService.deletar(id).subscribe({
        next: () => {
          alert('Produto removido com sucesso!');
          this.carregarProdutos();
        },
        error: (err) => alert('Erro ao remover produto. Verifique o backend.')
      });
    }
  }
}
