import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UsuarioService, UsuarioRequestDTO } from '../services/usuario.service';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
})
export class UsuariosComponent implements OnInit {
  usuarios: any[] = [];
  novoUsuario: UsuarioRequestDTO = { nome: '', email: '', telefone: '' };
  mensagemErro: string | null = null;
  mensagemSucesso: string | null = null;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.carregarUsuarios();
  }

  carregarUsuarios(): void {
    this.usuarioService.obterTodosUsuarios().subscribe({
      next: (data) => (this.usuarios = data),
      error: () => (this.mensagemErro = 'Erro ao carregar a lista de usuários.'),
    });
  }

  adicionarUsuario(): void {
    this.mensagemErro = null;
    this.mensagemSucesso = null;

    this.usuarioService.adicionarUsuario(this.novoUsuario).subscribe({
      next: () => {
        this.carregarUsuarios();
        this.novoUsuario = { nome: '', email: '', telefone: '' };
        this.mensagemSucesso = 'Usuário adicionado com sucesso!';
      },
      error: (err) => {
        this.mensagemErro =
          err.status === 400
            ? 'Erro ao adicionar usuário: Dados inválidos.'
            : 'Erro ao adicionar usuário. Tente novamente.';
      },
    });
  }
}
