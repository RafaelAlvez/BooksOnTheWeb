package br.com.books.web.emprestimo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String genero;
}
