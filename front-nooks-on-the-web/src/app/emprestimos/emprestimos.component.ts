import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EmprestimoService, EmprestimoRequestDTO, EmprestimoResponseDTO } from '../services/emprestimo.service';

@Component({
  selector: 'app-emprestimos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './emprestimos.component.html',
  styleUrls: ['./emprestimos.component.css'],
})
export class EmprestimosComponent implements OnInit {
  emprestimos: EmprestimoResponseDTO[] = [];
  novoEmprestimo: EmprestimoRequestDTO = {
    idLivro: 0,
    idUsuario: 0,
    dataDevolucao: '',
  };

  constructor(private emprestimoService: EmprestimoService) {}

  ngOnInit(): void {
    this.carregarEmprestimos();
  }

  carregarEmprestimos(): void {
    this.emprestimoService.listarEmprestimos().subscribe((data) => (this.emprestimos = data));
  }

  emprestar(): void {
    this.emprestimoService.emprestar(this.novoEmprestimo).subscribe(() => {
      this.carregarEmprestimos();
      this.novoEmprestimo = { idLivro: 0, idUsuario: 0, dataDevolucao: '' };
    });
  }

  devolver(id: number): void {
    this.emprestimoService.devolver(id).subscribe(() => this.carregarEmprestimos());
  }
}
