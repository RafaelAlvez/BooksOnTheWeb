package br.com.books.web.emprestimo.controller;

import br.com.books.web.emprestimo.model.Penalidade;
import br.com.books.web.emprestimo.service.impl.PenalidadeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PenalidadeControllerTest {

    @InjectMocks
    private PenalidadeController penalidadeController;

    @Mock
    private PenalidadeServiceImpl penalidadeService;

    @Mock
    private Penalidade penalidade;

    private Long idValido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        idValido = 1L;
    }

    @Test
    void testVerificarPenalidade_whenPenalidadeFound_shouldReturnOk() {
        when(penalidadeService.verificarPenalidade(idValido)).thenReturn(penalidade);
        var response = penalidadeController.verificarPenalidade(idValido);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(penalidade, response.getBody());
    }

    @Test
    void testVerificarPenalidade_whenPenalidadeNotFound_shouldReturnNotFound() {
        when(penalidadeService.verificarPenalidade(idValido)).thenReturn(null);
        var response = penalidadeController.verificarPenalidade(idValido);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void testVerificarPenalidade_whenExceptionThrown_shouldReturnBadRequest() {
        when(penalidadeService.verificarPenalidade(idValido)).thenThrow(new RuntimeException("Erro inesperado"));
        var response = penalidadeController.verificarPenalidade(idValido);
        assertEquals(400, response.getStatusCode().value());
    }
}
