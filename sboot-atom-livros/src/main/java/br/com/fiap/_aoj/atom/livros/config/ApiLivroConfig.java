package br.com.fiap._aoj.atom.livros.config;

import br.com.fiap._aoj.atom.livros.repository.LivroRepository;
import br.com.fiap._aoj.atom.livros.service.LivroService;
import br.com.fiap._aoj.atom.livros.service.impl.LivroServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiLivroConfig {

    @Bean
    public LivroService livroService(LivroRepository livroRepository){
        return new LivroServiceImpl(livroRepository);
    }

}