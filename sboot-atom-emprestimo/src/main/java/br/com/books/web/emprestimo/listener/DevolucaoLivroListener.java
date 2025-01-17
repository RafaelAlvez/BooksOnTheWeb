package br.com.books.web.emprestimo.listener;

import br.com.books.web.emprestimo.config.RabbitConfig;
import br.com.books.web.emprestimo.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DevolucaoLivroListener {

    private final EmprestimoService emprestimoService;

    @RabbitListener(queues = RabbitConfig.DEVOLUCAO_QUEUE)
    public void receiveMessage(Long id) {
        try {
            emprestimoService.devolver(id);
        } catch (Exception e) {
        }
    }
}
