import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LivroService, LivroDTO } from '../services/livro.service';

@Component({
  selector: 'app-livros',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './livros.component.html',
  styleUrls: ['./livros.component.css'],
})
export class LivrosComponent implements OnInit {
  livros: LivroDTO[] = [];
  novoLivro: Partial<LivroDTO> = { titulo: '', autor: '', genero: '', status: 'DISPONIVEL' };
  mensagemErro: string | null = null;
  mensagemSucesso: string | null = null;

  constructor(private livroService: LivroService) {}

  ngOnInit(): void {
    this.carregarLivros();
  }

  carregarLivros(): void {
    this.livroService.listarLivros().subscribe({
      next: (data) => (this.livros = data),
      error: () => (this.mensagemErro = 'Erro ao carregar a lista de livros.'),
    });
  }

  adicionarLivro(): void {
    this.mensagemErro = null;
    this.mensagemSucesso = null;

    this.livroService.adicionarLivro(this.novoLivro).subscribe({
      next: () => {
        this.carregarLivros();
        this.novoLivro = { titulo: '', autor: '', genero: '', status: 'DISPONIVEL' };
        this.mensagemSucesso = 'Livro adicionado com sucesso!';
      },
      error: () => {
        this.mensagemErro = 'Erro ao adicionar o livro. Tente novamente.';
      },
    });
  }
}
