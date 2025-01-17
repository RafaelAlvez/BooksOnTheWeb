package br.com.books.web.emprestimo.scheduler;

import br.com.books.web.emprestimo.service.EmprestimoService;
import br.com.books.web.emprestimo.service.PenalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@RequiredArgsConstructor
public class PenalidadeScheduler {

    private final EmprestimoService emprestimoService;
    private final PenalidadeService penalidadeService;

    @Async
    @Scheduled(cron = "0 0 1 1/1 * ?")
    public void verificarPenalidadesDiarias() {
        var emprestimosEmAtraso = emprestimoService.listarEmprestimosEmAtraso();
        emprestimosEmAtraso.forEach(e -> penalidadeService.verificarPenalidade(e, true));
    }
}
