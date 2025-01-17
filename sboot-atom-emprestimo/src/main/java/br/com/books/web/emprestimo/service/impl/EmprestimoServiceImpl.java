package br.com.books.web.emprestimo.service.impl;

import br.com.books.web.emprestimo.dto.DevolucaoResponseDTO;
import br.com.books.web.emprestimo.dto.EmprestimoRequestDTO;
import br.com.books.web.emprestimo.enums.StatusLivroEnum;
import br.com.books.web.emprestimo.exceptions.EmprestimoException;
import br.com.books.web.emprestimo.model.Emprestimo;
import br.com.books.web.emprestimo.model.Livro;
import br.com.books.web.emprestimo.model.Usuario;
import br.com.books.web.emprestimo.repository.EmprestimoRepository;
import br.com.books.web.emprestimo.repository.LivroRepository;
import br.com.books.web.emprestimo.repository.UsuarioRepository;
import br.com.books.web.emprestimo.service.EmprestimoService;
import br.com.books.web.emprestimo.service.PenalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final PenalidadeService penalidadeService;
    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    @Override
    @Transactional
    public Emprestimo emprestar(EmprestimoRequestDTO request) throws EmprestimoException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(request.getIdUsuario());
        if(usuarioOpt.isEmpty()) {
            throw new EmprestimoException("Usuario não encontrado");
        }
        var livro = livroRepository.findByIdAndStatus(request.getIdLivro(), StatusLivroEnum.DISPONIVEL);
        if(livro == null) {
            throw new EmprestimoException("Livro não disponivel para emprestimo");
        }
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuarioOpt.get());
        emprestimo.setDataEmprestimo(LocalDateTime.now());
        emprestimo.setLivro(livro);
        emprestimo.setDataDevolucaoPrevista(request.getDataDevolucao());
        livro.setStatus(StatusLivroEnum.EM_EMPRESTIMO);
        livroRepository.save(livro);
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
    @Transactional
    public DevolucaoResponseDTO devolver(Long id) throws EmprestimoException {
        var emprestimo = obterEmprestimo(id);
        if(emprestimo == null) {
            throw new EmprestimoException("Emprestimo não encontrado");
        }
        Livro livro = emprestimo.getLivro();
        livro.setStatus(StatusLivroEnum.DISPONIVEL);
        livroRepository.save(livro);

        var penalidade = penalidadeService.verificarPenalidade(emprestimo, false);
        emprestimo.setDevolvido(true);
        emprestimo.setDataDevolucaoReal(LocalDateTime.now());
        emprestimoRepository.save(emprestimo);
        boolean penalizado = penalidade != null;
        return new DevolucaoResponseDTO(
                penalizado,
                penalidade,
                getMensagemRetorno(penalizado),
                true
        );
    }

    @Override
    public List<Emprestimo> listarEmprestimosEmAtraso() {
        return emprestimoRepository.findByDataDevolucaoPrevistaGreaterThanAndDevolvido(LocalDateTime.now(), false);
    }

    private String getMensagemRetorno(boolean penalizado) {
        return penalizado ? "Livro devolvido em atraso. Consulte suas pendencias" : "Livro devolvido com sucesso";
    }
}
