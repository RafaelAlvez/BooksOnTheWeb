package br.com.books.web.emprestimo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EmprestimoRequestDTO {

    private Long idLivro;
    private Long idUsuario;
    private LocalDateTime dataDevolucao;
}
