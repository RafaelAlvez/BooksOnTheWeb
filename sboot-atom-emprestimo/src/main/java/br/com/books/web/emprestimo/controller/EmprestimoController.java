package br.com.books.web.emprestimo.controller;

import br.com.books.web.emprestimo.dto.DevolucaoResponseDTO;
import br.com.books.web.emprestimo.dto.EmprestimoRequestDTO;
import br.com.books.web.emprestimo.dto.EmprestimoResponseDTO;
import br.com.books.web.emprestimo.mapper.EmprestimoMapper;
import br.com.books.web.emprestimo.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> emprestar(@RequestBody EmprestimoRequestDTO request) {
        try {
            var emprestimo = emprestimoService.emprestar(request);
            return ResponseEntity.ok(EmprestimoMapper.toResponseDTO(emprestimo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> obterEmprestimo(@PathVariable Long id) {
        try{
            var emprestimo = emprestimoService.obterEmprestimo(id);
            return ResponseEntity.ok(EmprestimoMapper.toResponseDTO(emprestimo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> listarEmprestimos() {
        try {
            var emprestimos = emprestimoService.listarEmprestimos();
            var responseList = emprestimos.stream().map(EmprestimoMapper::toResponseDTO).toList();
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DevolucaoResponseDTO> devolver(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(emprestimoService.devolver(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
