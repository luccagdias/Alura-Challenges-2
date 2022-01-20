package br.com.alura.challenges.financas.exception.standard;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class StandardException {

    private Integer status;

    private String message;

    private LocalDateTime dateAndTime;

    public StandardException(Integer status, String message, LocalDateTime dateAndTime) {
        this.status = status;
        this.message = message;
        this.dateAndTime = dateAndTime;
    }
}