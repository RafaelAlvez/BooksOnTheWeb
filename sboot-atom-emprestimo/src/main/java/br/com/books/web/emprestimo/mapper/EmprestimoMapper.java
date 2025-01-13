package br.com.books.web.emprestimo.mapper;

import br.com.books.web.emprestimo.dto.EmprestimoResponseDTO;
import br.com.books.web.emprestimo.dto.LivroResponseDTO;
import br.com.books.web.emprestimo.model.Emprestimo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmprestimoMapper {

    public static EmprestimoResponseDTO toResponseDTO(Emprestimo emprestimo) {
        EmprestimoResponseDTO emprestimoResponseDTO = new EmprestimoResponseDTO();
        emprestimoResponseDTO.setId(emprestimo.getId());
        emprestimoResponseDTO.setDevolvido(emprestimo.getDevolvido());
        emprestimoResponseDTO.setIdUsuario(emprestimo.getUsuario().getId());
        emprestimoResponseDTO.setDataDevolucao(emprestimo.getDataDevolucaoPrevista());

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO();
        livroResponseDTO.setId(emprestimo.getLivro().getId());
        livroResponseDTO.setTitulo(emprestimo.getLivro().getTitulo());
        livroResponseDTO.setAutor(emprestimo.getLivro().getAutor());
        livroResponseDTO.setGenero(emprestimo.getLivro().getGenero());

        emprestimoResponseDTO.setLivro(livroResponseDTO);
        return emprestimoResponseDTO;
    }
}
