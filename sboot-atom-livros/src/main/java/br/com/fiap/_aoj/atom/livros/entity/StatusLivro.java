package br.com.fiap._aoj.atom.livros.entity;

import br.com.fiap._aoj.atom.livros.exception.LivroIllegalArgumentException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusLivro {

    DISPONIVEL(1),
    INDISPONIVEL(2),
    EM_EMPRESTIMO(3);

    private Integer idStatus;

    public static StatusLivro fromCodigo(int codigo) {
        return Arrays.stream(values())
                .filter(status -> status.getIdStatus() == codigo)
                .findFirst()
                .orElseThrow(() -> new LivroIllegalArgumentException("Código inválido: " + codigo));
    }

    public static StatusLivro fromNome(String nome) {
        return Arrays.stream(StatusLivro.values())
                .filter(status -> status.name().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new LivroIllegalArgumentException("Nome inválido para StatusLivro: " + nome));
    }


}