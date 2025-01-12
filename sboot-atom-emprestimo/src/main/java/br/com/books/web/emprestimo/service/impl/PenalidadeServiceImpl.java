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
            return internalVerificarPenalidade(emprestimo);
        }
        return null;
    }

    @Override
    public Penalidade verificarPenalidade(Emprestimo emprestimo) {
        return internalVerificarPenalidade(emprestimo);
    }

    private Penalidade internalVerificarPenalidade(Emprestimo emprestimo) {
        if (emprestimo.getDataDevolucaoReal() != null && emprestimo.getDataDevolucaoReal().isAfter(emprestimo.getDataDevolucaoPrevista())) {
            long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataDevolucaoReal());
            BigDecimal penalidadeValor = BigDecimal.valueOf(diasAtraso * 2);
            Penalidade penalidade = new Penalidade();
            penalidade.setValor(penalidadeValor);
            penalidade.setUsuario(emprestimo.getUsuario());
            penalidade.setDataAplicacao(LocalDate.now());
            return penalidadeRepository.save(penalidade);
        }
        return null;
    }
}
