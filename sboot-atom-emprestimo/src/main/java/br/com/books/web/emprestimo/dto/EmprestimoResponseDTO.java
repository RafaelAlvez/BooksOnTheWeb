package br.com.books.web.emprestimo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EmprestimoResponseDTO {

    private Long id;
    private Long idUsuario;
    private LivroResponseDTO livro;
    private LocalDateTime dataDevolucao;
    private boolean devolvido;
}
