import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { EmprestimoService, EmprestimoRequestDTO, EmprestimoResponseDTO } from '../services/emprestimo.service';

@Component({
  selector: 'app-emprestimos',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './emprestimos.component.html',
  styleUrls: ['./emprestimos.component.css'],
})
export class EmprestimosComponent implements OnInit {
  emprestimos: EmprestimoResponseDTO[] = [];
  novoEmprestimo: EmprestimoRequestDTO = { idLivro: 0, idUsuario: 0, dataDevolucao: '' };
  mensagemErro: string | null = null;
  mensagemSucesso: string | null = null;

  constructor(private emprestimoService: EmprestimoService) {}

  ngOnInit(): void {
    this.carregarEmprestimos();
  }

  carregarEmprestimos(): void {
    this.emprestimoService.listarEmprestimos().subscribe({
      next: (data) => (this.emprestimos = data),
      error: () => (this.mensagemErro = 'Erro ao carregar os empréstimos.'),
    });
  }

  emprestar(): void {
    this.mensagemErro = null;
    this.mensagemSucesso = null;

    this.emprestimoService.emprestar(this.novoEmprestimo).subscribe({
      next: () => {
        this.carregarEmprestimos();
        this.novoEmprestimo = { idLivro: 0, idUsuario: 0, dataDevolucao: '' };
        this.mensagemSucesso = 'Empréstimo realizado com sucesso!';
      },
      error: () => {
        this.mensagemErro = 'Erro ao realizar o empréstimo. Tente novamente.';
      },
    });
  }

  devolver(id: number): void {
    this.mensagemErro = null;
    this.mensagemSucesso = null;

    this.emprestimoService.devolver(id).subscribe({
      next: () => {
        this.carregarEmprestimos();
        this.mensagemSucesso = 'Livro devolvido com sucesso!';
      },
      error: () => {
        this.mensagemErro = 'Erro ao devolver o livro. Tente novamente.';
      },
    });
  }
}
