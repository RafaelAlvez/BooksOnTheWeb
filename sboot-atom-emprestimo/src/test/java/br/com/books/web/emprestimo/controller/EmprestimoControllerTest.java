package br.com.books.web.emprestimo.controller;

import br.com.books.web.emprestimo.dto.DevolucaoResponseDTO;
import br.com.books.web.emprestimo.dto.EmprestimoRequestDTO;
import br.com.books.web.emprestimo.exceptions.EmprestimoException;
import br.com.books.web.emprestimo.model.Emprestimo;
import br.com.books.web.emprestimo.model.Livro;
import br.com.books.web.emprestimo.model.Usuario;
import br.com.books.web.emprestimo.service.EmprestimoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmprestimoControllerTest {
    @Mock
    private EmprestimoService emprestimoService;

    @InjectMocks
    private EmprestimoController emprestimoController;

    private MockMvc mockMvc;

    private Emprestimo emprestimo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(emprestimoController).build();

        emprestimo = new Emprestimo();
        emprestimo.setId(1L);
        var usuario = new Usuario();
        usuario.setId(1L);
        var livro = new Livro();
        livro.setId(1L);
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
    }

    @Test
    void testEmprestar() throws Exception {
        when(emprestimoService.emprestar(any(EmprestimoRequestDTO.class))).thenReturn(emprestimo);
        mockMvc.perform(post("/v1/emprestimos")
                        .contentType("application/json")
                        .content("{\"id\":1, \"usuarioId\":1, \"titulo\":\"Livro Teste\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn();
        verify(emprestimoService, times(1)).emprestar(any(EmprestimoRequestDTO.class));
    }

    @Test
    void testEmprestar_whenExceptionThrown_shouldReturnBadRequest() throws EmprestimoException {
        when(emprestimoService.emprestar(any())).thenThrow(new RuntimeException("Erro inesperado"));
        var response = emprestimoController.emprestar(new EmprestimoRequestDTO());
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    void testObterEmprestimo() throws Exception {
        when(emprestimoService.obterEmprestimo(1L)).thenReturn(emprestimo);
        mockMvc.perform(get("/v1/emprestimos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        verify(emprestimoService, times(1)).obterEmprestimo(1L);
    }

    @Test
    void testObterEmprestimo_whenExceptionThrown_shouldReturnBadRequest() {
        when(emprestimoService.obterEmprestimo(anyLong())).thenThrow(new RuntimeException("Erro inesperado"));
        var response = emprestimoController.obterEmprestimo(1L);
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    void testListarEmprestimos() throws Exception {
        List<Emprestimo> emprestimos = List.of(emprestimo);
        when(emprestimoService.listarEmprestimos()).thenReturn(emprestimos);
        mockMvc.perform(get("/v1/emprestimos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
        verify(emprestimoService, times(1)).listarEmprestimos();
    }

    @Test
    void testListarEmprestimos_whenExceptionThrown_shouldReturnBadRequest() {
        when(emprestimoService.listarEmprestimos()).thenThrow(new RuntimeException("Erro inesperado"));
        var response = emprestimoController.listarEmprestimos();
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    void testDevolver() throws Exception {
        DevolucaoResponseDTO devolucaoResponseDTO = new DevolucaoResponseDTO(true, null, "", true);
        when(emprestimoService.devolver(1L)).thenReturn(devolucaoResponseDTO);
        mockMvc.perform(put("/v1/emprestimos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.devolvido").value(true));
        verify(emprestimoService, times(1)).devolver(1L);
    }

    @Test
    void testDevolver_whenExceptionThrown_shouldReturnBadRequest() throws EmprestimoException {
        when(emprestimoService.devolver(anyLong())).thenThrow(new RuntimeException("Erro inesperado"));
        var response = emprestimoController.devolver(1L);
        assertEquals(400, response.getStatusCode().value());
    }
}
