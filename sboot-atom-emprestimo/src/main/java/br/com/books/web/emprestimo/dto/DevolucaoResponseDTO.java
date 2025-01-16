package br.com.books.web.emprestimo.dto;

import br.com.books.web.emprestimo.model.Penalidade;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DevolucaoResponseDTO {

    private boolean penalizado;
    private Penalidade penalidade;
    private String message;
    private boolean devolvido;
}
