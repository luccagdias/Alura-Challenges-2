package br.com.alura.challenges.financas.dto.request;

import br.com.alura.challenges.financas.entity.Receita;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class ReceitaRequestDTO {

    private String descricao;

    private BigDecimal valor;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate data;

    public static Receita toEntity(ReceitaRequestDTO receitaRequestDTO) {
        return Receita.builder()
                .descricao(receitaRequestDTO.getDescricao())
                .valor(receitaRequestDTO.getValor())
                .data(receitaRequestDTO.getData())
                .build();
    }
}
