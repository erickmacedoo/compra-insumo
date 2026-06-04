import { Component, signal } from '@angular/core';
import { FormProdutoComponent } from './components/form-produto/form-produto';
import { ListaProdutos } from './components/lista-produtos/lista-produtos';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormProdutoComponent, ListaProdutos],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  title = 'compra-insumo-front';
}
