import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {

  private apiUrl = 'https://backend-compra-insumo.onrender.com/api/produtos';

  constructor(private http: HttpClient) { }

  listarTodos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  salvar(produto: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, produto);
  }

  atualizar(id: number, produto: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, produto);
  }
  
  deletar(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
}

}
