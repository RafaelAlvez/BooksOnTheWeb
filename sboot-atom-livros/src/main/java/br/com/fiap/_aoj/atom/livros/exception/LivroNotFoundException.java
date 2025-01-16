package br.com.fiap._aoj.atom.livros.exception;

import br.com.fiap._aoj.atom.livros.utils.LivroUtils;

public class LivroNotFoundException extends RuntimeException {

    private static String MSG_ERROR = "Livro id: {} não encontrado.";

    public LivroNotFoundException(Long idLivro) {
        super(LivroUtils.replaceString(MSG_ERROR, String.valueOf(idLivro)));
    }

}