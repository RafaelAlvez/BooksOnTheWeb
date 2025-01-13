package br.com.books.web.emprestimo.service.impl;

import br.com.books.web.emprestimo.dto.DevolucaoResponseDTO;
import br.com.books.web.emprestimo.dto.EmprestimoRequestDTO;
import br.com.books.web.emprestimo.model.Emprestimo;
import br.com.books.web.emprestimo.model.Livro;
import br.com.books.web.emprestimo.model.Penalidade;
import br.com.books.web.emprestimo.model.Usuario;
import br.com.books.web.emprestimo.repository.EmprestimoRepository;
import br.com.books.web.emprestimo.repository.LivroRepository;
import br.com.books.web.emprestimo.repository.UsuarioRepository;
import br.com.books.web.emprestimo.service.PenalidadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmprestimoServiceImplTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private PenalidadeService penalidadeService;

    @InjectMocks
    private EmprestimoServiceImpl emprestimoService;

    private Emprestimo emprestimo;
    private EmprestimoRequestDTO emprestimoRequest;
    private Usuario usuario;
    private Livro livro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emprestimo = new Emprestimo();
        usuario = new Usuario();
        livro = new Livro();
        emprestimo.setId(1L);
        usuario.setId(1L);
        livro.setId(1L);
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDevolvido(false);

        emprestimoRequest = new EmprestimoRequestDTO();
        emprestimoRequest.setDataDevolucao(LocalDateTime.now());
        emprestimoRequest.setIdUsuario(1L);
        emprestimoRequest.setIdLivro(1L);
    }

    @Test
    void testEmprestar() {
        when(emprestimoRepository.save(any())).thenReturn(emprestimo);
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(livroRepository.findByIdAndStatus(anyLong(), any())).thenReturn(livro);
        Emprestimo result = emprestimoService.emprestar(emprestimoRequest);
        verify(emprestimoRepository, times(1)).save(any());
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
