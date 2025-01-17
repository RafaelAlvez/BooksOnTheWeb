package br.com.books.web.usuarios.controller;

import br.com.books.web.usuarios.dto.UsuarioRequestDTO;
import br.com.books.web.usuarios.dto.UsuarioResponseDTO;
import br.com.books.web.usuarios.exceptions.UsuarioNaoEncontradoException;
import br.com.books.web.usuarios.mapper.UsuarioMapper;
import br.com.books.web.usuarios.model.Usuario;
import br.com.books.web.usuarios.service.UsuarioService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;
    @Mock
    private UsuarioService usuarioService;

    @Test
    void testObterTodosUsuarios() {
        List<Usuario> usuarios = List.of(getUsuario());
        when(usuarioService.obterTodosUsuarios()).thenReturn(usuarios);
        ResponseEntity<List<UsuarioResponseDTO>> response = usuarioController.obterTodosUsuarios();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @SneakyThrows
    @Test
    void testObterUsuarioPorId() {
        when(usuarioService.obterUsuarioPorId(1L)).thenReturn(getUsuario());
        ResponseEntity<UsuarioResponseDTO> response = usuarioController.obterUsuarioPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(UsuarioMapper.toResponseDTO(getUsuario()), response.getBody());
    }

    @SneakyThrows
    @Test
    void testObterUsuarioPorIdNaoEncontrado() {
        when(usuarioService.obterUsuarioPorId(1L)).thenThrow(new UsuarioNaoEncontradoException("Usuário não encontrado"));
        ResponseEntity<UsuarioResponseDTO> response = usuarioController.obterUsuarioPorId(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAdicionarUsuario() {
        when(usuarioService.adicionarUsuario(any())).thenReturn(getUsuario());
        ResponseEntity<UsuarioResponseDTO> response = usuarioController.adicionarUsuario(getUsuarioRequestDTO());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(UsuarioMapper.toResponseDTO(getUsuario()), response.getBody());
    }

    @SneakyThrows
    @Test
    void testAtualizarUsuario() {
        when(usuarioService.atualizarUsuario(eq(1L), any())).thenReturn(getUsuario());
        ResponseEntity<UsuarioResponseDTO> response = usuarioController.atualizarUsuario(1L, getUsuarioRequestDTO());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(UsuarioMapper.toResponseDTO(getUsuario()), response.getBody());
    }

    @SneakyThrows
    @Test
    void testDeletarUsuario() {
        doNothing().when(usuarioService).deletarUsuario(1L);
        ResponseEntity<Void> response = usuarioController.deletarUsuario(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private UsuarioRequestDTO getUsuarioRequestDTO() {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setNome("TST");
        usuarioRequestDTO.setEmail("email@email.com");
        return usuarioRequestDTO;
    }

    private Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("TST");
        usuario.setEmail("email@email.com");
        return usuario;
    }

}

