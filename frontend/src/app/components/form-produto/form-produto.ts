import { Component, EventEmitter, Output } from '@angular/core';
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
  
  categoriaSelecionada: string = ''; 
  categorias: string[] = ['Frutas', 'Verduras', 'Legumes', 'Temperos', 'Cereais e Grãos'];

  mensagemSucesso: string = '';
  mensagemErro: string = '';

  constructor(private produtoService: ProdutoService) {}

  salvar(): void {
    console.log("Botão foi Clicado. Dados atuais:", this.produto, this.categoriaSelecionada)
    this.mensagemSucesso = '';
    this.mensagemErro = '';

    if (!this.produto.nome || !this.categoriaSelecionada) {
      this.mensagemErro = 'Por favor, preencha o nome e selecione uma categoria.';
      return;
    }

    this.produtoService.salvar({ nome: this.produto.nome, categoria: this.categoriaSelecionada }).subscribe({
      next: (res) => {
        this.mensagemSucesso = 'Produto cadastrado com sucesso!';
        this.produto = { nome: '' };
        this.categoriaSelecionada = '';
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
  this.categoriaSelecionada = produtoSelecionado.categoria;
}


  atualizar(): void {
    if (!this.produto.id) return;

    this.produtoService.atualizar(this.produto.id, {
      nome: this.produto.nome,
      categoria: this.categoriaSelecionada
    }).subscribe({
      next: (res) => {
        this.mensagemSucesso = 'Produto atualizado com sucesso!';
        this.produto = { nome: '' }; // Limpa o form
        this.categoriaSelecionada = '';
        this.produtoSalvo.emit(); // Atualiza a tabela na tela
      },
      error: (err) => this.mensagemErro = 'Erro ao atualizar produto.'
    });
  }
}
