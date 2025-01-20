import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface LivroDTO {
  id: number;
  titulo: string;
  autor: string;
  genero: string;
  status: string;
}

@Injectable({
  providedIn: 'root',
})
export class LivroService {
  private apiUrl = 'http://localhost:8080/api/livros';

  constructor(private http: HttpClient) {}

  // Adicionar um novo livro
  adicionarLivro(livro: Partial<LivroDTO>): Observable<LivroDTO> {
    return this.http.post<LivroDTO>(this.apiUrl, livro);
  }

  // Atualizar o status de um livro
  atualizarLivro(id: number, status: string): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/status`, { status });
  }

  // Consultar um livro pelo ID
  consultarLivro(id: number): Observable<LivroDTO> {
    return this.http.get<LivroDTO>(`${this.apiUrl}/${id}`);
  }

  // Listar todos os livros
  listarLivros(): Observable<LivroDTO[]> {
    return this.http.get<LivroDTO[]>(this.apiUrl);
  }
}
