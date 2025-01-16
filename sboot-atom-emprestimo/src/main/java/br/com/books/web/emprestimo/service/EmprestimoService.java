package br.com.books.web.emprestimo.service;

import br.com.books.web.emprestimo.dto.DevolucaoResponseDTO;
import br.com.books.web.emprestimo.dto.EmprestimoRequestDTO;
import br.com.books.web.emprestimo.model.Emprestimo;

import java.util.List;

public interface EmprestimoService {

    Emprestimo obterEmprestimo(Long reservaId);
    Emprestimo emprestar(EmprestimoRequestDTO request);
    List<Emprestimo> listarEmprestimos();
    List<Emprestimo> listarEmprestimosPorUsuario(Long idUsuario);
    DevolucaoResponseDTO devolver(Long id);
}
