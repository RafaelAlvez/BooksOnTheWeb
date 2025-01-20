import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UsuarioService, UsuarioRequestDTO, UsuarioResponseDTO } from '../services/usuario.service';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
})
export class UsuariosComponent implements OnInit {
  usuarios: UsuarioResponseDTO[] = [];
  novoUsuario: UsuarioRequestDTO = { nome: '', email: '', telefone: '' };
  usuarioSelecionado: UsuarioResponseDTO | null = null;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.carregarUsuarios();
  }

  carregarUsuarios(): void {
    this.usuarioService.obterTodosUsuarios().subscribe((data) => (this.usuarios = data));
  }

  adicionarUsuario(): void {
    this.usuarioService.adicionarUsuario(this.novoUsuario).subscribe(() => {
      this.carregarUsuarios();
      this.novoUsuario = { nome: '', email: '', telefone: '' };
    });
  }

  editarUsuario(usuario: UsuarioResponseDTO): void {
    this.usuarioSelecionado = { ...usuario };
  }

  atualizarUsuario(): void {
    if (this.usuarioSelecionado) {
      this.usuarioService
        .atualizarUsuario(this.usuarioSelecionado.idUsuario, this.usuarioSelecionado)
        .subscribe(() => {
          this.carregarUsuarios();
          this.usuarioSelecionado = null;
        });
    }
  }

  deletarUsuario(id: number): void {
    this.usuarioService.deletarUsuario(id).subscribe(() => this.carregarUsuarios());
  }
}
