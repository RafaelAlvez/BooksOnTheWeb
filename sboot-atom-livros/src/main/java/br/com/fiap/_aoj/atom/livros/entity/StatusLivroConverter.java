package br.com.fiap._aoj.atom.livros.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class StatusLivroConverter implements AttributeConverter<StatusLivro, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusLivro statusLivro) {
        return Objects.isNull(statusLivro) ? null : statusLivro.getIdStatus();
    }

    @Override
    public StatusLivro convertToEntityAttribute(Integer idStatus) {
        return Objects.isNull(idStatus) ? null : StatusLivro.fromCodigo(idStatus);
    }
}
