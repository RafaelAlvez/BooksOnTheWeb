import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PenalidadeService, PenalidadeDTO } from '../services/penalidade.service';

@Component({
  selector: 'app-penalidades',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './penalidades.component.html',
  styleUrls: ['./penalidades.component.css'],
})
export class PenalidadesComponent {
  penalidades: PenalidadeDTO[] = [];
  idUsuario: number | null = null;
  mensagemErro: string | null = null;

  constructor(private penalidadeService: PenalidadeService) {}

  verificarPenalidade(): void {
    if (!this.idUsuario || this.idUsuario <= 0) {
      this.mensagemErro = 'Por favor, insira um ID de usuário válido.';
      return;
    }

    this.mensagemErro = null;
    this.penalidadeService.verificarPenalidade(this.idUsuario).subscribe({
      next: (data) => {
        this.penalidades = [data];
      },
      error: (err) => {
        if (err.status === 404) {
          this.mensagemErro = 'Penalidade não encontrada para este usuário.';
        } else {
          this.mensagemErro = 'Erro ao buscar penalidade. Tente novamente.';
        }
        this.penalidades = [];
      },
    });
  }
}
