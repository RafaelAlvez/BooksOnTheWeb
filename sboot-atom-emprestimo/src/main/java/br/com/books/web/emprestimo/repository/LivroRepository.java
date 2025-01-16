package br.com.books.web.emprestimo.repository;

import br.com.books.web.emprestimo.enums.StatusLivroEnum;
import br.com.books.web.emprestimo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Livro findByIdAndStatus(Long id, StatusLivroEnum status);
}
