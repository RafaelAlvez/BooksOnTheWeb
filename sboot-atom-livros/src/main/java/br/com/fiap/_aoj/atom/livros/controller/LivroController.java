package br.com.fiap._aoj.atom.livros.controller;

import br.com.fiap._aoj.atom.livros.entity.StatusLivro;
import br.com.fiap._aoj.atom.livros.service.LivroService;
import br.com.fiap.aoj.atom.livros.api.V1Api;
import br.com.fiap.aoj.atom.livros.model.Livro;
import br.com.fiap.aoj.atom.livros.model.LivroInput;
import br.com.fiap.aoj.atom.livros.model.LivroOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LivroController implements V1Api {

    private @Autowired LivroService livroService;

    @Override
    public ResponseEntity<LivroOutput> adicionarLivro(LivroInput livro) {
        LivroOutput livroOutput = livroService.adicionarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroOutput);
    }

    @Override
    public ResponseEntity<Void> atualizarLivro(Long id, String status) {
        livroService.atualizarLivro(id, StatusLivro.fromNome(status));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Livro> consultarLivro(Long id) {
        var livro = livroService.consultarLivro(id);
        return ResponseEntity.ok(livro);
    }

    @Override
    public ResponseEntity<List<Livro>> listarLivros() {
        var livros = livroService.consultarLivros();
        return ResponseEntity.ok(livros);
    }

}