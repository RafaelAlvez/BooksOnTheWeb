package br.com.books.web.emprestimo.service.impl;

import br.com.books.web.emprestimo.model.Emprestimo;
import br.com.books.web.emprestimo.model.Penalidade;
import br.com.books.web.emprestimo.repository.EmprestimoRepository;
import br.com.books.web.emprestimo.repository.PenalidadeRepository;
import br.com.books.web.emprestimo.service.PenalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PenalidadeServiceImpl implements PenalidadeService {

    private final PenalidadeRepository penalidadeRepository;
    private final EmprestimoRepository emprestimoRepository;

    @Override
    @Transactional
    public Penalidade verificarPenalidade(Long emprestimoId) {
        Optional<Emprestimo> emprestimoOpt = emprestimoRepository.findById(emprestimoId);
        if (emprestimoOpt.isPresent()) {
            var emprestimo = emprestimoOpt.get();
            return internalVerificarPenalidade(emprestimo, false);
        }
        return null;
    }

    @Override
    public Penalidade verificarPenalidade(Emprestimo emprestimo, boolean scheduler) {
        return internalVerificarPenalidade(emprestimo, scheduler);
    }

    private Penalidade internalVerificarPenalidade(Emprestimo emprestimo, boolean scheduler) {
        if(scheduler) {
           return internalCriarPenalidade(emprestimo, ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), LocalDateTime.now()));
        } else {
            if (emprestimo.getDataDevolucaoReal() != null && emprestimo.getDataDevolucaoReal().isAfter(emprestimo.getDataDevolucaoPrevista())) {
                return internalCriarPenalidade(emprestimo, ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataDevolucaoReal()));
            }
        }
        return null;
    }

    private Penalidade internalCriarPenalidade(Emprestimo emprestimo, long diasAtraso) {
        BigDecimal penalidadeValor = BigDecimal.valueOf(diasAtraso * 2);
        Penalidade penalidade = new Penalidade();
        penalidade.setValor(penalidadeValor);
        penalidade.setUsuario(emprestimo.getUsuario());
        penalidade.setDataAplicacao(LocalDate.now());
        return penalidadeRepository.save(penalidade);
    }
}
