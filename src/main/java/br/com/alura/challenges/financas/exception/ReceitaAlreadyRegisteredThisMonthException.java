package br.com.alura.challenges.financas.exception;

public class ReceitaAlreadyRegisteredThisMonthException extends RuntimeException {
    public ReceitaAlreadyRegisteredThisMonthException() {
        super("Uma receita com a mesma descrição já foi cadastrada no mês indicado");
    }
}
