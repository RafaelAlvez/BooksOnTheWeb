package br.com.books.web.emprestimo.service.impl;

import br.com.books.web.emprestimo.model.Emprestimo;
import br.com.books.web.emprestimo.model.Penalidade;
import br.com.books.web.emprestimo.model.Usuario;
import br.com.books.web.emprestimo.repository.EmprestimoRepository;
import br.com.books.web.emprestimo.repository.PenalidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class PenalidadeServiceImplTest {

    @Mock
    private PenalidadeRepository penalidadeRepository;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @InjectMocks
    private PenalidadeServiceImpl penalidadeService;

    private Emprestimo emprestimo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emprestimo = new Emprestimo();
        emprestimo.setId(1L);
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataDevolucaoPrevista(LocalDate.of(2025, 1, 1).atStartOfDay());
        emprestimo.setDataDevolucaoReal(LocalDate.of(2025, 1, 5).atStartOfDay());
    }

    @Test
    void testVerificarPenalidadePorId() {
        when(emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));
        when(penalidadeRepository.save(any())).thenReturn(new Penalidade());
        Penalidade penalidade = penalidadeService.verificarPenalidade(1L);
        assertNotNull(penalidade);
        verify(emprestimoRepository, times(1)).findById(1L);
        verify(penalidadeRepository, times(1)).save(any());
    }

    @Test
    void testVerificarPenalidade() {
        when(penalidadeRepository.save(any())).thenReturn(new Penalidade());
        var response = penalidadeService.verificarPenalidade(emprestimo, true);
        assertNotNull(response);
    }

    @Test
    void testVerificarPenalidadeSemAtraso() {
        Emprestimo emprestimoSemAtraso = new Emprestimo();
        emprestimoSemAtraso.setId(2L);
        Usuario usuario = new Usuario();
        usuario.setId(2L);
        emprestimoSemAtraso.setUsuario(usuario);
        emprestimoSemAtraso.setDataDevolucaoPrevista(LocalDate.of(2025, 1, 1).atStartOfDay());
        emprestimoSemAtraso.setDataDevolucaoReal(LocalDate.of(2025, 1, 1).atStartOfDay());
        when(emprestimoRepository.findById(2L)).thenReturn(Optional.of(emprestimoSemAtraso));
        Penalidade penalidade = penalidadeService.verificarPenalidade(2L);
        assertNull(penalidade);
    }

    @Test
    void testVerificarPenalidadeEmprestimoNaoEncontrado() {
        when(emprestimoRepository.findById(99L)).thenReturn(Optional.empty());
        Penalidade penalidade = penalidadeService.verificarPenalidade(99L);
        assertNull(penalidade);
    }

}
