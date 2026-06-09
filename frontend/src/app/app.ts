import { Component, signal } from '@angular/core';
import { FormProdutoComponent } from './components/form-produto/form-produto';
import { ListaProdutos } from './components/lista-produtos/lista-produtos';
import { ListaCategorias } from './components/lista-categorias/lista-categorias';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormProdutoComponent, ListaProdutos, ListaCategorias],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  title = 'compra-insumo-front';
}
