package br.com.fiap._aoj.atom.livros.exception;

import br.com.fiap.aoj.atom.livros.model.Error;
import br.com.fiap.aoj.atom.livros.model.LivroNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class LivroExceptionHandler {

    @ExceptionHandler(LivroNotFoundException.class)
    public ResponseEntity<LivroNotFound> handleLivroNotFound(LivroNotFoundException ex, WebRequest wr) {
        return ResponseEntity.status(404).body(new LivroNotFound()
                .error(HttpStatus.NOT_FOUND.name())
                .message(ex.getMessage())
                .path(wr.getContextPath()));
    }

    @ExceptionHandler(LivroIllegalArgumentException.class)
    public ResponseEntity<Error> handleLivroIllegalArgument(LivroIllegalArgumentException exception, WebRequest wr) {
        return ResponseEntity.status(422).body(new Error()
                .error(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .message(exception.getMessage())
                .path(wr.getContextPath()));
    }

}