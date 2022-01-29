package br.com.alura.challenges.financas.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Categoria {
    ALIMENTAÇÃO("Alimentação"),
    SAÚDE("Saúde"),
    MORADIA("Moradia"),
    TRANSPORTE("Transporte"),
    EDUCAÇÃO("Educação"),
    LAZER("Lazer"),
    IMPREVISTOS("Imprevistos"),
    OUTRAS("Outras");

    private String text;

    Categoria(String text) {
        this.text = text;
    }

    @JsonCreator
    public static Categoria fromString(String text) {
        return text == null
                ? null
                : Categoria.valueOf(text.toUpperCase());
    }

    @JsonValue
    public String getText() {
        return text;
    }
}
