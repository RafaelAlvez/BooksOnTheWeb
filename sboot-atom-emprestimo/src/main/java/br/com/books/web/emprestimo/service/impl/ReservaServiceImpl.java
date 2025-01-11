package br.com.books.web.emprestimo.service.impl;

import br.com.books.web.emprestimo.model.Penalidade;
import br.com.books.web.emprestimo.model.Reserva;
import br.com.books.web.emprestimo.repository.PenalidadeRepository;
import br.com.books.web.emprestimo.repository.ReservaRepository;
import br.com.books.web.emprestimo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final PenalidadeRepository penalidadeRepository;

    @Override
    public Penalidade verificarPenalidade(Long reservaId) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(reservaId);
        if (reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();
            if (reserva.getDataDevolucaoReal() != null && reserva.getDataDevolucaoReal().isAfter(reserva.getDataDevolucaoPrevista())) {
                long diasAtraso = reserva.getDataDevolucaoReal().toEpochDay() - reserva.getDataDevolucaoPrevista().toEpochDay();
                BigDecimal penalidadeValor = BigDecimal.valueOf(diasAtraso * 2);
                Penalidade penalidade = new Penalidade();
                penalidade.setReserva(reserva);
                penalidade.setValor(penalidadeValor);
                penalidade.setDataAplicacao(LocalDate.now());
                return penalidadeRepository.save(penalidade);
            }
        }
        return null;
    }

    @Override
    public Reserva criarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva obterReserva(Long reservaId) {
        return reservaRepository.findById(reservaId).orElse(null);
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
}
