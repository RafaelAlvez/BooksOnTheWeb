package br.com.books.web.emprestimo.service;

import br.com.books.web.emprestimo.model.Emprestimo;
import br.com.books.web.emprestimo.model.Penalidade;

public interface PenalidadeService {

    Penalidade verificarPenalidade(Long emprestimoId);
    Penalidade verificarPenalidade(Emprestimo emprestimo);
}
