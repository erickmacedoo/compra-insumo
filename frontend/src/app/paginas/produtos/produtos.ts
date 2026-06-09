import { Component } from '@angular/core';
import { FormProdutoComponent } from '../../components/form-produto/form-produto';
import { ListaProdutos } from '../../components/lista-produtos/lista-produtos';

@Component({
  selector: 'app-produtos',
  standalone: true,
  imports: [FormProdutoComponent, ListaProdutos],
  templateUrl: './produtos.html',
  styleUrl: './produtos.css',
})
export class ProdutosComponent {

}
