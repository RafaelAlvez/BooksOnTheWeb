package br.com.fiap._aoj.atom.livros.service;

import br.com.fiap._aoj.atom.livros.entity.StatusLivro;
import br.com.fiap.aoj.atom.livros.model.Livro;
import br.com.fiap.aoj.atom.livros.model.LivroInput;
import br.com.fiap.aoj.atom.livros.model.LivroOutput;

import java.util.List;

public interface LivroService {

    List<Livro> consultarLivros();
    Livro consultarLivro(Long idLivro);
    LivroOutput adicionarLivro(LivroInput livro);
    void atualizarLivro(Long id, StatusLivro statusLivro);
    void deletarLivro(Long id);

}