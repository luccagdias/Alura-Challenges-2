package br.com.alura.challenges.financas.exception.advice;

import br.com.alura.challenges.financas.exception.ValidationException;
import br.com.alura.challenges.financas.exception.standard.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardException> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        ValidationException errors = new ValidationException(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}