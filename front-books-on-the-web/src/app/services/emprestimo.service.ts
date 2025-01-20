import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface EmprestimoRequestDTO {
  idLivro: number;
  idUsuario: number;
  dataDevolucao: string; // ISO 8601 format (e.g., "2025-01-20T15:30:00")
}

export interface EmprestimoResponseDTO {
  id: number;
  idUsuario: number;
  livro: LivroResponseDTO;
  dataDevolucao: string;
  devolvido: boolean;
}

export interface LivroResponseDTO {
  id: number;
  titulo: string;
  autor: string;
  genero: string;
}

export interface DevolucaoResponseDTO {
  penalizado: boolean;
  penalidade: any; // Defina melhor conforme necessário
  message: string;
  devolvido: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class EmprestimoService {
  private apiUrl = 'http://localhost:8080/v1/emprestimos';

  constructor(private http: HttpClient) {}

  // Criar um novo empréstimo
  emprestar(request: EmprestimoRequestDTO): Observable<EmprestimoResponseDTO> {
    return this.http.post<EmprestimoResponseDTO>(this.apiUrl, request);
  }

  // Obter detalhes de um empréstimo pelo ID
  obterEmprestimo(id: number): Observable<EmprestimoResponseDTO> {
    return this.http.get<EmprestimoResponseDTO>(`${this.apiUrl}/${id}`);
  }

  // Listar todos os empréstimos
  listarEmprestimos(): Observable<EmprestimoResponseDTO[]> {
    return this.http.get<EmprestimoResponseDTO[]>(this.apiUrl);
  }

  // Devolver um empréstimo
  devolver(id: number): Observable<DevolucaoResponseDTO> {
    return this.http.put<DevolucaoResponseDTO>(`${this.apiUrl}/${id}`, null);
  }
}
