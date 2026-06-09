import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-lista-categorias',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './lista-categorias.html',
  styleUrls: ['./lista-categorias.css']
})
export class ListaCategorias implements OnInit {
  categorias: any[] = [];
  categoriaEmEdicao: any = null;
  nomeEditando: string = '';
  mensagemSucesso: string = '';
  mensagemErro: string = '';

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit(): void {
    this.carregarCategorias();
  }

  carregarCategorias(): void {
    this.categoriaService.listarTodas().subscribe({
      next: (dados) => this.categorias = dados,
      error: () => this.mensagemErro = 'Erro ao carregar categorias.'
    });
  }

  iniciarEdicao(categoria: any): void {
    this.categoriaEmEdicao = categoria;
    this.nomeEditando = categoria.nome;
  }

  salvarEdicao(): void {
    if (!this.nomeEditando.trim()) {
      this.mensagemErro = 'O nome não pode ser vazio.';
      return;
    }
    this.categoriaService.atualizar(this.categoriaEmEdicao.id, { nome: this.nomeEditando }).subscribe({
      next: () => {
        this.mensagemSucesso = 'Categoria atualizada!';
        this.mensagemErro = '';
        this.categoriaEmEdicao = null;
        this.carregarCategorias();
      },
      error: () => this.mensagemErro = 'Erro ao atualizar categoria.'
    });
  }

  cancelarEdicao(): void {
    this.categoriaEmEdicao = null;
    this.nomeEditando = '';
  }

  remover(id: number): void {
    if (confirm('Tem certeza que deseja remover esta categoria?')) {
      this.categoriaService.deletar(id).subscribe({
        next: () => {
          this.mensagemSucesso = 'Categoria removida!';
          this.carregarCategorias();
        },
        error: () => this.mensagemErro = 'Erro ao remover. Verifique se há produtos vinculados.'
      });
    }
  }
}