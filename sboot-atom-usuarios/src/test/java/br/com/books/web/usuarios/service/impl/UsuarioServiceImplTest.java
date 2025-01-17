package br.com.books.web.usuarios.service.impl;

import br.com.books.web.usuarios.dto.UsuarioRequestDTO;
import br.com.books.web.usuarios.exceptions.UsuarioNaoEncontradoException;
import br.com.books.web.usuarios.model.Usuario;
import br.com.books.web.usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {
    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João");
        usuario.setEmail("joao@example.com");
        usuario.setTelefone("123456789");
        usuario.setStatus(true);
        usuario.setCriadoEm(LocalDateTime.now());
    }

    @Test
    void testObterTodosUsuarios() {
        List<Usuario> usuarios = List.of(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> result = usuarioService.obterTodosUsuarios();
        assertEquals(usuarios, result);
    }

    @Test
    void testObterUsuarioPorId() throws UsuarioNaoEncontradoException {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Usuario result = usuarioService.obterUsuarioPorId(1L);
        assertEquals(usuario, result);
    }

    @Test
    void testObterUsuarioPorIdNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.obterUsuarioPorId(1L));
    }

    @Test
    void testAdicionarUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario result = usuarioService.adicionarUsuario(getUsuarioRequestDTO());
        assertEquals(usuario.getNome(), result.getNome());
        assertEquals(usuario.getEmail(), result.getEmail());
        assertEquals(usuario.getTelefone(), result.getTelefone());
    }

    @Test
    void testAtualizarUsuario() throws UsuarioNaoEncontradoException {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario result = usuarioService.atualizarUsuario(1L, getUsuarioRequestDTO());
        assertEquals(getUsuarioRequestDTO().getNome(), result.getNome());
        assertEquals(getUsuarioRequestDTO().getEmail(), result.getEmail());
    }

    @Test
    void testDeletarUsuario() throws UsuarioNaoEncontradoException {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);
        usuarioService.deletarUsuario(1L);
        verify(usuarioRepository, times(1)).delete(usuario);
    }


    private UsuarioRequestDTO getUsuarioRequestDTO() {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setNome("TST");
        usuarioRequestDTO.setEmail("email@email.com");
        return usuarioRequestDTO;
    }

}