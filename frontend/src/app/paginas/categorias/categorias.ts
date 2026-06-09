import { Component } from '@angular/core';
import { ListaCategorias } from '../../components/lista-categorias/lista-categorias';

@Component({
  selector: 'app-categorias',
  standalone: true,
  imports: [ListaCategorias],
  templateUrl: './categorias.html',
  styleUrl: './categorias.css',
})
export class CategoriasComponent {

}
