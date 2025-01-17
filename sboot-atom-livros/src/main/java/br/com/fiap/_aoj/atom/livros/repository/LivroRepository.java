package br.com.fiap._aoj.atom.livros.repository;

import br.com.fiap._aoj.atom.livros.entity.LivroEntity;
import br.com.fiap._aoj.atom.livros.entity.StatusLivro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

    @Modifying
    @Transactional
    @Query("update livro set status = :statusId, atualizadoEm = :atualizadoEm where id = :id")
    int atualizarLivro(@Param("statusId") StatusLivro statusId, @Param("atualizadoEm") OffsetDateTime atualizadoEm, @Param("id") Long id);

}