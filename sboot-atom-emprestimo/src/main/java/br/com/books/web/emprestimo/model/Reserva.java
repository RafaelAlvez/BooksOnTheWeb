package br.com.books.web.emprestimo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "tb_reserva")
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro livro;

    private String usuario;
    private LocalDate dataReserva;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
}
