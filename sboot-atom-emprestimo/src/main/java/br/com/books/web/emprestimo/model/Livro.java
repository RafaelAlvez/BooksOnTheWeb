package br.com.books.web.emprestimo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "tb_livro")
@Data
public class Livro {

    @Id
    @GeneratedValue
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;

}
