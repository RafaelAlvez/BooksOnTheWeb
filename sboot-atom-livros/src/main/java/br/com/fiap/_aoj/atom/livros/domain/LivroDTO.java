package br.com.fiap._aoj.atom.livros.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    private String status;

}