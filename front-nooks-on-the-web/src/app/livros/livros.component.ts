import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LivroService, LivroDTO } from '../services/livro.service';

@Component({
  selector: 'app-livros',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './livros.component.html',
  styleUrls: ['./livros.component.css'],
})
export class LivrosComponent implements OnInit {
  livros: LivroDTO[] = [];
  novoLivro: Partial<LivroDTO> = {
    titulo: '',
    autor: '',
    genero: '',
    status: 'DISPONIVEL',
  };

  constructor(private livroService: LivroService) {}

  ngOnInit(): void {
    this.carregarLivros();
  }

  carregarLivros(): void {
    this.livroService.listarLivros().subscribe((data) => (this.livros = data));
  }

  adicionarLivro(): void {
    this.livroService.adicionarLivro(this.novoLivro).subscribe(() => {
      this.carregarLivros();
      this.novoLivro = { titulo: '', autor: '', genero: '', status: 'DISPONIVEL' };
    });
  }

  atualizarStatus(id: number, status: string): void {
    this.livroService.atualizarLivro(id, status).subscribe(() => this.carregarLivros());
  }

  editarLivro(livro: LivroDTO): void {
    console.log('Função de edição chamada para:', livro);
  }
}
