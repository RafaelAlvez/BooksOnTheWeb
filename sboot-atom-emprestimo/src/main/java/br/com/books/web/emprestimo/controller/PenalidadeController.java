package br.com.books.web.emprestimo.controller;

import br.com.books.web.emprestimo.model.Penalidade;
import br.com.books.web.emprestimo.service.PenalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/penalidades")
@RequiredArgsConstructor
public class PenalidadeController {

    private final PenalidadeService penalidadeService;

    @GetMapping("/{id}")
    public Penalidade verificarPenalidade(@PathVariable Long id) {
        return penalidadeService.verificarPenalidade(id);
    }

}
