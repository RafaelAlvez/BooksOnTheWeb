import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PenalidadeService, PenalidadeDTO } from '../services/penalidade.service';

@Component({
  selector: 'app-penalidades',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './penalidades.component.html',
  styleUrls: ['./penalidades.component.css'],
})
export class PenalidadesComponent {
  idUsuario: number | null = null;
  penalidade: PenalidadeDTO | null = null;
  mensagemErro: string | null = null;

  constructor(private penalidadeService: PenalidadeService) {}

  verificarPenalidade(): void {
    if (this.idUsuario !== null) {
      this.penalidadeService.verificarPenalidade(this.idUsuario).subscribe(
        (data) => {
          this.penalidade = data;
          this.mensagemErro = null;
        },
        (error) => {
          if (error.status === 404) {
            this.mensagemErro = 'Penalidade não encontrada para este usuário.';
          } else {
            this.mensagemErro = 'Erro ao buscar penalidade. Tente novamente.';
          }
          this.penalidade = null;
        }
      );
    }
  }
}

