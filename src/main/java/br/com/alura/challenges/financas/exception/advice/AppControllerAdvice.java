package br.com.alura.challenges.financas.exception.advice;

import br.com.alura.challenges.financas.exception.AlreadyRegisteredThisMonthException;
import br.com.alura.challenges.financas.exception.ValidationException;
import br.com.alura.challenges.financas.exception.standard.StandardException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
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
                "Os parâmetros devem ser valores numéricos",
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

    @ExceptionHandler(AlreadyRegisteredThisMonthException.class)
    public ResponseEntity<StandardException> alreadyRegisteredThisMonthExceptionHandler(AlreadyRegisteredThisMonthException exception) {
        StandardException standardException = new StandardException(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardException);
    }

    @ExceptionHandler(ValueInstantiationException.class)
    public ResponseEntity<StandardException> valueInstantiationExceptionHandler(ValueInstantiationException exception) {
        StandardException standardException = new StandardException(
                HttpStatus.BAD_REQUEST.value(),
                "As categorias válidas são: Alimentação, Saúde, Moradia, Transporte, Educação, Lazer, Imprevistos ou Outras",
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardException);
    }
}