package br.com.alura.challenges.financas.dto.request;

import br.com.alura.challenges.financas.entity.Despesa;
import br.com.alura.challenges.financas.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class DespesaRequestDTO {

    @NotEmpty(message = "Campo 'descricao' deve ser preenchido")
    private String descricao;

    @NotNull (message = "Campo 'valor' deve ser preenchido")
    private BigDecimal valor;

    @NotNull(message = "Campo 'data' deve ser preenchido")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate data;

    private Categoria categoria;

    public static Despesa toEntity(DespesaRequestDTO despesaRequestDTO) {
        if (despesaRequestDTO.getCategoria() == null) {
            despesaRequestDTO.categoria = Categoria.OUTRAS;
        }

        return Despesa.builder()
                .descricao(despesaRequestDTO.getDescricao())
                .valor(despesaRequestDTO.getValor())
                .data(despesaRequestDTO.getData())
                .categoria(despesaRequestDTO.getCategoria())
                .build();
    }
}
