package br.com.books.web.usuarios.service.impl;

import br.com.books.web.usuarios.dto.UsuarioRequestDTO;
import br.com.books.web.usuarios.exceptions.UsuarioNaoEncontradoException;
import br.com.books.web.usuarios.model.Usuario;
import br.com.books.web.usuarios.repository.UsuarioRepository;
import br.com.books.web.usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obterUsuarioPorId(Long id) throws UsuarioNaoEncontradoException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        return optionalUsuario.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com id: " + id));
    }

    @Override
    public Usuario adicionarUsuario(UsuarioRequestDTO usuario) {
        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setNome(usuario.getNome());
        usuarioNovo.setEmail(usuario.getEmail());
        usuarioNovo.setTelefone(usuario.getTelefone());
        usuarioNovo.setStatus(true);
        usuarioNovo.setCriadoEm(LocalDateTime.now());
        return usuarioRepository.save(usuarioNovo);
    }

    @Override
    public Usuario atualizarUsuario(Long id, UsuarioRequestDTO detalhesUsuario) throws UsuarioNaoEncontradoException {
        Usuario usuario = obterUsuarioPorId(id);
        usuario.setNome(detalhesUsuario.getNome());
        usuario.setEmail(detalhesUsuario.getEmail());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deletarUsuario(Long id) throws UsuarioNaoEncontradoException {
        Usuario usuario = obterUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }
}
