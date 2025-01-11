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
    private Reserva reserva;

    private BigDecimal valor;
    private LocalDate dataAplicacao;
}
