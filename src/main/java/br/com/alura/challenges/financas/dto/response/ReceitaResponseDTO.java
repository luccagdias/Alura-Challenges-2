package br.com.alura.challenges.financas.dto.response;

import br.com.alura.challenges.financas.entity.Receita;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ReceitaResponseDTO {

    private Long id;

    private String descricao;

    private BigDecimal valor;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate data;

    public static ReceitaResponseDTO toResponseDTO(Receita receita) {
        return ReceitaResponseDTO.builder()
                .id(receita.getId())
                .descricao(receita.getDescricao())
                .valor(receita.getValor())
                .data(receita.getData())
                .build();
    }

    public static List<ReceitaResponseDTO> entityListToResponseDTOList(List<Receita> receitas) {
        return receitas.stream().map(receita -> toResponseDTO(receita)).collect(Collectors.toList());
    }
}
