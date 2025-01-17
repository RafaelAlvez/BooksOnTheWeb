package br.com.books.web.usuarios.mapper;

import br.com.books.web.usuarios.dto.UsuarioResponseDTO;
import br.com.books.web.usuarios.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setIdUsuario(usuario.getId());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setEmail(usuario.getEmail());
        usuarioResponseDTO.setTelefone(usuario.getTelefone());
        return usuarioResponseDTO;
    }
}
