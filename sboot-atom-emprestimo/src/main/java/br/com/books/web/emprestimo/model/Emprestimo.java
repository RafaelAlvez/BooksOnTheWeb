package br.com.books.web.emprestimo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimo")
@Data
@NoArgsConstructor
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;
    @Column(name = "data_emprestimo", updatable = false)
    private LocalDateTime dataEmprestimo;
    @Column(name = "data_devolucao_prevista")
    private LocalDateTime dataDevolucaoPrevista;
    @Column(name = "data_devolucao_real")
    private LocalDateTime dataDevolucaoReal;
    @Column(nullable = false)
    private Boolean devolvido = false;
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

}
