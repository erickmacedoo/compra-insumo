import { Component, EventEmitter, Output } from '@angular/core';
import { CategoriaService } from '../../services/categoria.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProdutoService } from '../../services/produto.service';

@Component({
  selector: 'app-form-produto',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './form-produto.html',
  styleUrls: ['./form-produto.css']
})

export class FormProdutoComponent {
  @Output() produtoSalvo = new EventEmitter<void>();
  produto: any = { nome: '' }; 
  
  categorias: any[] = [];
  categoriaSelecionadaId: number | null = null;

  mensagemSucesso: string = '';
  mensagemErro: string = '';

  constructor(private produtoService: ProdutoService, private categoriaService: CategoriaService) {
    this.carregarCategorias();
  }

  carregarCategorias(): void {
    this.categoriaService.listarTodas().subscribe({
      next: (dados) => this.categorias = dados,
      error: () => console.error('Erro ao carregar categorias')
    });
  }

  salvar(): void {
    console.log("Botão foi Clicado. Dados atuais:", this.produto, this.categoriaSelecionadaId)
    this.mensagemSucesso = '';
    this.mensagemErro = '';

    if (!this.produto.nome || !this.categoriaSelecionadaId) {
      this.mensagemErro = 'Por favor, preencha o nome e selecione uma categoria.';
      return;
    }

    this.produtoService.salvar({ nome: this.produto.nome, categoriaId: this.categoriaSelecionadaId }).subscribe({
      next: (res) => {
        this.mensagemSucesso = 'Produto cadastrado com sucesso!';
        this.produto = { nome: '' };
        this.categoriaSelecionadaId = null;
        this.produtoSalvo.emit();

        this.atualizar();
      },
      error: (err) => {
        console.error(err);
        this.mensagemErro = 'Erro ao cadastrar. Verifique se o servidor está rodando.';
      }
    });
  }

  carregarProdutoNoForm(produtoSelecionado: any) {
  this.produto = { ...produtoSelecionado };
  this.categoriaSelecionadaId = produtoSelecionado.categoria?.id ?? null;
}


  atualizar(): void {
    if (!this.produto.id) return;

    this.produtoService.atualizar(this.produto.id, {
      nome: this.produto.nome,
      categoriaId: this.categoriaSelecionadaId
    }).subscribe({
      next: (res) => {
        this.mensagemSucesso = 'Produto atualizado com sucesso!';
        this.produto = { nome: '' };
        this.categoriaSelecionadaId = null;
        this.produtoSalvo.emit();
      },
      error: (err) => this.mensagemErro = 'Erro ao atualizar produto.'
    });
  }
}
