package br.com.alura.challenges.financas.exception.advice;

import br.com.alura.challenges.financas.exception.ReceitaAlreadyRegisteredThisMonthException;
import br.com.alura.challenges.financas.exception.ValidationException;
import br.com.alura.challenges.financas.exception.standard.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

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

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<StandardException> numberFormatExceptionHandler(NumberFormatException exception) {
        StandardException standardException = new StandardException(
                HttpStatus.BAD_REQUEST.value(),
                "O id deve ser um valor numérico",
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardException);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardException> noSuchElementExceptionHandler(NoSuchElementException exception) {
        StandardException standardException = new StandardException(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardException);
    }

    @ExceptionHandler(ReceitaAlreadyRegisteredThisMonthException.class)
    public ResponseEntity<StandardException> receitaAlreadyRegisteredThisMonthExceptionHandler(ReceitaAlreadyRegisteredThisMonthException exception) {
        StandardException standardException = new StandardException(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardException);
    }
}