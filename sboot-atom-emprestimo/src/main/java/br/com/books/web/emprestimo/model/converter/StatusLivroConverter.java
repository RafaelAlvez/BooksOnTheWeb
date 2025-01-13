package br.com.books.web.emprestimo.model.converter;

import br.com.books.web.emprestimo.enums.StatusLivroEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusLivroConverter implements AttributeConverter<StatusLivroEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusLivroEnum attribute) {
        return attribute == null ? null : attribute.getStatus();
    }

    @Override
    public StatusLivroEnum convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : StatusLivroEnum.fromStatus(dbData);
    }
}
