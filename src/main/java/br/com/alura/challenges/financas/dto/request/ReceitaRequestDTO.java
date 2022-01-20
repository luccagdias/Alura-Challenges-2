package br.com.alura.challenges.financas.dto.request;

import br.com.alura.challenges.financas.entity.Receita;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class ReceitaRequestDTO {

    @NotEmpty(message = "Campo 'descricao' deve ser preenchido")
    private String descricao;

    @NotNull (message = "Campo 'valor' deve ser preenchido")
    private BigDecimal valor;

    @NotNull(message = "Campo 'data' deve ser preenchido")
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
