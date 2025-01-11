package br.com.books.web.emprestimo.service;

import br.com.books.web.emprestimo.model.Penalidade;
import br.com.books.web.emprestimo.model.Reserva;

import java.util.List;

public interface ReservaService {

    Penalidade verificarPenalidade(Long reservaId);
    Reserva obterReserva(Long reservaId);
    Reserva criarReserva(Reserva reserva);
    List<Reserva> listarReservas();
}
