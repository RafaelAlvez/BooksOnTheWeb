package br.com.books.web.usuarios.controller;

import br.com.books.web.usuarios.dto.UsuarioRequestDTO;
import br.com.books.web.usuarios.dto.UsuarioResponseDTO;
import br.com.books.web.usuarios.exceptions.UsuarioNaoEncontradoException;
import br.com.books.web.usuarios.mapper.UsuarioMapper;
import br.com.books.web.usuarios.model.Usuario;
import br.com.books.web.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obterTodosUsuarios() {
        var usuarios = usuarioService.obterTodosUsuarios();
        return ResponseEntity.ok(usuarios.stream().map(UsuarioMapper::toResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obterUsuarioPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.obterUsuarioPorId(id);
            return ResponseEntity.ok(UsuarioMapper.toResponseDTO(usuario));
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> adicionarUsuario(@RequestBody UsuarioRequestDTO usuario) {
        try {
            Usuario novoUsuario = usuarioService.adicionarUsuario(usuario);
            return ResponseEntity.ok(UsuarioMapper.toResponseDTO(novoUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO detalhesUsuario) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, detalhesUsuario);
            return ResponseEntity.ok(UsuarioMapper.toResponseDTO(usuarioAtualizado));
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
