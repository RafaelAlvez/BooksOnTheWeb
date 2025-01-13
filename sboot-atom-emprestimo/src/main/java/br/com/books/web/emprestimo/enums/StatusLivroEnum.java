package br.com.books.web.emprestimo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum StatusLivroEnum {

    DISPONIVEL(1),
    INDISPONIVEL(2),
    EM_EMPRESTIMO(3);

    private final int status;

    public static StatusLivroEnum fromStatus(int status) {
        for (StatusLivroEnum statusLivroEnum : StatusLivroEnum.values())
            if (statusLivroEnum.getStatus() == status) return statusLivroEnum;
        return null;
    }
}
