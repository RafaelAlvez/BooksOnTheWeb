package br.com.books.web.emprestimo.repository;

import br.com.books.web.emprestimo.model.Penalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenalidadeRepository extends JpaRepository<Penalidade, Long> {
}
