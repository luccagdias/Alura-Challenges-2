package br.com.alura.challenges.financas.controller;

import br.com.alura.challenges.financas.dto.request.DespesaRequestDTO;
import br.com.alura.challenges.financas.dto.response.DespesaResponseDTO;
import br.com.alura.challenges.financas.entity.Despesa;
import br.com.alura.challenges.financas.service.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    private DespesaService service;

    public DespesaController(DespesaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> findById(@PathVariable String id) {
        Despesa despesa = service.findById(id);

        return ResponseEntity.ok().body(DespesaResponseDTO.toResponseDTO(despesa));
    }

    @GetMapping
    public ResponseEntity<List<DespesaResponseDTO>> findAll(@RequestParam(required = false) String descricao) {
        List<Despesa> despesas =
                (descricao == null) ? service.findAll() : service.findAllByDescricao(descricao);

        return ResponseEntity.ok().body(DespesaResponseDTO.entityListToResponseDTOList(despesas));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DespesaRequestDTO despesaRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        Despesa despesa = DespesaRequestDTO.toEntity(despesaRequestDTO);
        despesa = service.save(despesa);

        URI uri = uriComponentsBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> update(@Valid @RequestBody DespesaRequestDTO despesaRequestDTO, @PathVariable String id) {
        Despesa despesa = DespesaRequestDTO.toEntity(despesaRequestDTO);
        despesa = service.update(despesa, id);

        return ResponseEntity.ok(DespesaResponseDTO.toResponseDTO(despesa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.ok("Despesa excluída com sucesso");
    }
}
