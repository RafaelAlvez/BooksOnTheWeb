package br.com.books.web.usuarios.service;

import br.com.books.web.usuarios.dto.UsuarioRequestDTO;
import br.com.books.web.usuarios.exceptions.UsuarioNaoEncontradoException;
import br.com.books.web.usuarios.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> obterTodosUsuarios();

    Usuario obterUsuarioPorId(Long id) throws UsuarioNaoEncontradoException;

    Usuario adicionarUsuario(UsuarioRequestDTO usuario);

    Usuario atualizarUsuario(Long id, UsuarioRequestDTO detalhesUsuario) throws UsuarioNaoEncontradoException;

    void deletarUsuario(Long id) throws UsuarioNaoEncontradoException;
}
