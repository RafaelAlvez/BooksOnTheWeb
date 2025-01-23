import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PenalidadeDTO {
  id: number;
  usuario: {
    idUsuario: number;
    nome: string;
    email: string;
    telefone: string;
  };
  valor: string; // BigDecimal como string para evitar problemas com precisão
  dataAplicacao: string; // Formato ISO (e.g., "2025-01-20")
}

@Injectable({
  providedIn: 'root',
})
export class PenalidadeService {
  private apiUrl = 'http://localhost:8087/v1/penalidades';

  constructor(private http: HttpClient) {}

  // Verificar penalidade pelo ID do usuário
  verificarPenalidade(id: number): Observable<PenalidadeDTO> {
    return this.http.get<PenalidadeDTO>(`${this.apiUrl}/${id}`);
  }
}
