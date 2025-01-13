package br.com.books.web.emprestimo.repository;

import br.com.books.web.emprestimo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
