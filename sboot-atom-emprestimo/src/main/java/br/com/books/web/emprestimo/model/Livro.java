package br.com.books.web.emprestimo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String autor;
    @Column(nullable = false)
    private String genero;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusLivro status;
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "atualizado_em")
    private LocalDateTime dataAtualizacao;

}
