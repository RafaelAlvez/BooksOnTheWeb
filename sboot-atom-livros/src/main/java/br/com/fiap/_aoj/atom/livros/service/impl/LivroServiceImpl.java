package br.com.fiap._aoj.atom.livros.service.impl;

import br.com.fiap._aoj.atom.livros.entity.LivroEntity;
import br.com.fiap._aoj.atom.livros.entity.StatusLivro;
import br.com.fiap._aoj.atom.livros.exception.LivroNotFoundException;
import br.com.fiap._aoj.atom.livros.repository.LivroRepository;
import br.com.fiap._aoj.atom.livros.service.LivroService;
import br.com.fiap.aoj.atom.livros.model.Livro;
import br.com.fiap.aoj.atom.livros.model.LivroInput;
import br.com.fiap.aoj.atom.livros.model.LivroOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

import static br.com.fiap._aoj.atom.livros.mapper.ResourceMapper.*;

@Service
public class LivroServiceImpl implements LivroService {

    private static final Logger log = LoggerFactory.getLogger(LivroServiceImpl.class);

    private LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public List<Livro> consultarLivros() {
        List<LivroEntity> all = livroRepository.findAll();
        return toListDto(all);
    }

    @Override
    public Livro consultarLivro(Long idLivro) {
        var lEntity = livroRepository.findById(idLivro).
                orElseThrow((() -> new LivroNotFoundException(idLivro)));

        return toModel(lEntity);
    }

    @Override
    public void atualizarLivro(Long id, StatusLivro statusLivro) {
        livroRepository.atualizarLivro(statusLivro, OffsetDateTime.now(), id);
    }

    @Override
    public void deletarLivro(Long id) {
        try {
            livroRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public LivroOutput adicionarLivro(LivroInput livro) {
        LivroEntity livroEntity = livroRepository.saveAndFlush(toEntity(livro));
        return toOutoput(livroEntity);
    }

}