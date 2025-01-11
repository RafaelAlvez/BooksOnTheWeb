package br.com.books.web.emprestimo.controller;

import br.com.books.web.emprestimo.model.Penalidade;
import br.com.books.web.emprestimo.model.Reserva;
import br.com.books.web.emprestimo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva criarReserva(@RequestBody Reserva reserva) {
        return reservaService.criarReserva(reserva);
    }

    @GetMapping("/{id}")
    public Reserva obterReserva(@PathVariable Long id) {
        return reservaService.obterReserva(id);
    }

    @GetMapping("/{id}/penalidade")
    public Penalidade verificarPenalidade(@PathVariable Long id) {
        return reservaService.verificarPenalidade(id);
    }

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.listarReservas();
    }
}
