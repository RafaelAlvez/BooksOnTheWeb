package br.com.books.web.emprestimo.controller;

import br.com.books.web.emprestimo.dto.DevolucaoResponseDTO;
import br.com.books.web.emprestimo.model.Emprestimo;
import br.com.books.web.emprestimo.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Emprestimo emprestar(@RequestBody Emprestimo emprestimo) {
        return emprestimoService.emprestar(emprestimo);
    }

    @GetMapping("/{id}")
    public Emprestimo obterEmprestimo(@PathVariable Long id) {
        return emprestimoService.obterEmprestimo(id);
    }

    @GetMapping
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoService.listarEmprestimos();
    }

    @PutMapping("/{id}")
    public DevolucaoResponseDTO devolver(@PathVariable Long id) {
        return emprestimoService.devolver(id);
    }
}
