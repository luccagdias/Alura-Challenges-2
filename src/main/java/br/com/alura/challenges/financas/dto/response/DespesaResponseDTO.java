package br.com.alura.challenges.financas.dto.response;

import br.com.alura.challenges.financas.entity.Despesa;
import br.com.alura.challenges.financas.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class DespesaResponseDTO {

    private Long id;

    private String descricao;

    private BigDecimal valor;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate data;

    private Categoria categoria;

    public static DespesaResponseDTO toResponseDTO(Despesa despesa) {
        return DespesaResponseDTO.builder()
                .id(despesa.getId())
                .descricao(despesa.getDescricao())
                .valor(despesa.getValor())
                .data(despesa.getData())
                .categoria(despesa.getCategoria())
                .build();
    }

    public static List<DespesaResponseDTO> entityListToResponseDTOList(List<Despesa> despesas) {
        return despesas.stream().map(despesa -> toResponseDTO(despesa)).collect(Collectors.toList());
    }
}
