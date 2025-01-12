package br.com.books.web.emprestimo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "status_livro")
@Data
@NoArgsConstructor
public class StatusLivro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "atualizado_em")
    private LocalDateTime dataAtualizacao;

}
