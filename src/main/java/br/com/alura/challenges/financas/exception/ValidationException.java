package br.com.alura.challenges.financas.exception;

import br.com.alura.challenges.financas.exception.standard.StandardException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationException extends StandardException {

    @Getter
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationException(Integer status, LocalDateTime dateAndTime) {
        super(status, "Erro de validação", dateAndTime);
    }

    public void addValidationError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}