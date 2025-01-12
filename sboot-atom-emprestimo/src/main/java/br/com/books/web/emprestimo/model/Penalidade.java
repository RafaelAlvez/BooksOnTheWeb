package br.com.books.web.emprestimo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "tb_penalidade")
@Data
public class Penalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal valor;
    @Column(name = "data_aplicacao", nullable = false)
    private LocalDate dataAplicacao;
}
