package br.com.books.web.emprestimo.service.impl;

import br.com.books.web.emprestimo.dto.DevolucaoResponseDTO;
import br.com.books.web.emprestimo.model.Emprestimo;
import br.com.books.web.emprestimo.model.Penalidade;
import br.com.books.web.emprestimo.model.Usuario;
import br.com.books.web.emprestimo.repository.EmprestimoRepository;
import br.com.books.web.emprestimo.service.PenalidadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmprestimoServiceImplTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private PenalidadeService penalidadeService;

    @InjectMocks
    private EmprestimoServiceImpl emprestimoService;

    private Emprestimo emprestimo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emprestimo = new Emprestimo();
        emprestimo.setId(1L);
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        emprestimo.setUsuario(usuario);
        emprestimo.setDevolvido(false);
    }

    @Test
    void testEmprestar() {
        when(emprestimoRepository.save(any())).thenReturn(emprestimo);
        Emprestimo result = emprestimoService.emprestar(emprestimo);
        verify(emprestimoRepository, times(1)).save(emprestimo);
        assertNotNull(result);
        assertEquals(emprestimo.getId(), result.getId());
    }

    @Test
    void testObterEmprestimo() {
        when(emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));
        Emprestimo result = emprestimoService.obterEmprestimo(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        when(emprestimoRepository.findById(2L)).thenReturn(Optional.empty());
        Emprestimo resultNotFound = emprestimoService.obterEmprestimo(2L);
        assertNull(resultNotFound);
    }

    @Test
    void testListarEmprestimos() {
        when(emprestimoRepository.findAll()).thenReturn(Collections.singletonList(emprestimo));
        var result = emprestimoService.listarEmprestimos();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(emprestimo.getId(), result.getFirst().getId());
    }

    @Test
    void testListarEmprestimosPorUsuario() {
        when(emprestimoRepository.findByUsuarioId(1L)).thenReturn(Collections.singletonList(emprestimo));
        var result = emprestimoService.listarEmprestimosPorUsuario(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.getFirst().getUsuario().getId());
    }

    @Test
    void testDevolverComPenalidade() {
        when(emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));
        when(penalidadeService.verificarPenalidade(emprestimo)).thenReturn(new Penalidade());
        DevolucaoResponseDTO response = emprestimoService.devolver(1L);
        assertNotNull(response);
        assertTrue(response.isPenalizado());
    }

    @Test
    void testDevolverEmprestimoNaoEncontrado() {
        when(emprestimoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> emprestimoService.devolver(99L));
    }
}
