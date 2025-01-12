package br.com.books.web.emprestimo.service.impl;

import br.com.books.web.emprestimo.dto.DevolucaoResponseDTO;
import br.com.books.web.emprestimo.model.Emprestimo;
import br.com.books.web.emprestimo.repository.EmprestimoRepository;
import br.com.books.web.emprestimo.service.EmprestimoService;
import br.com.books.web.emprestimo.service.PenalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final PenalidadeService penalidadeService;

    @Override
    public Emprestimo emprestar(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public Emprestimo obterEmprestimo(Long emprestimoId) {
        return emprestimoRepository.findById(emprestimoId).orElse(null);
    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    @Override
    public List<Emprestimo> listarEmprestimosPorUsuario(Long idUsuario) {
        return emprestimoRepository.findByUsuarioId(idUsuario);
    }

    @Override
    public DevolucaoResponseDTO devolver(Long id) {
        var emprestimo = obterEmprestimo(id);
        if(emprestimo == null) {
            throw new RuntimeException("Emprestimo não encontrado");
        }
        var penalidade = penalidadeService.verificarPenalidade(emprestimo);
        return new DevolucaoResponseDTO(penalidade != null, penalidade, "", true);
    }
}
