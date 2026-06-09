import { Routes } from '@angular/router';
import { ProdutosComponent } from './paginas/produtos/produtos';
import { CategoriasComponent } from './paginas/categorias/categorias';

export const routes: Routes = [
  { path: '', redirectTo: '/produtos', pathMatch: 'full' },
  { path: 'produtos', component: ProdutosComponent },
  { path: 'categorias', component: CategoriasComponent }
];