import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UsuarioService, UsuarioRequestDTO, UsuarioResponseDTO } from '../services/usuario.service';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
})
export class UsuariosComponent implements OnInit {
  usuarios: UsuarioResponseDTO[] = [];
  novoUsuario: UsuarioRequestDTO = { nome: '', email: '', telefone: '' };
  usuarioSelecionado: UsuarioResponseDTO | null = null;
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
      error: () => {
        this.mensagemErro = 'Erro ao adicionar usuário. Tente novamente.';
      },
    });
  }

  selecionarUsuario(usuario: UsuarioResponseDTO): void {
    this.usuarioSelecionado = { ...usuario };
  }

  atualizarUsuario(): void {
    if (this.usuarioSelecionado) {
      const confirmacao = confirm('Você tem certeza de que deseja atualizar este usuário?');
      if (confirmacao) {
        this.usuarioService
          .atualizarUsuario(this.usuarioSelecionado.idUsuario, this.usuarioSelecionado)
          .subscribe({
            next: () => {
              this.carregarUsuarios();
              this.usuarioSelecionado = null;
              this.mensagemSucesso = 'Usuário atualizado com sucesso!';
            },
            error: () => {
              this.mensagemErro = 'Erro ao atualizar usuário. Tente novamente.';
            },
          });
      }
    }
  }


  deletarUsuario(id: number): void {
    const confirmacao = confirm('Você tem certeza de que deseja excluir este usuário?');
    if (confirmacao) {
      this.usuarioService.deletarUsuario(id).subscribe({
        next: () => {
          this.carregarUsuarios();
          this.mensagemSucesso = 'Usuário deletado com sucesso!';
        },
        error: () => {
          this.mensagemErro = 'Erro ao deletar usuário. Tente novamente.';
        },
      });
    }
  }

}
