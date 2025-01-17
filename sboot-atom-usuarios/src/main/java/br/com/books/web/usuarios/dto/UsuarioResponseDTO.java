package br.com.books.web.usuarios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String nome;
    private String email;
    private String telefone;
}
