package br.com.alura.challenges.financas.exception;

public class AlreadyRegisteredThisMonthException extends RuntimeException {
    public AlreadyRegisteredThisMonthException(String message) {
        super(message);
    }
}
