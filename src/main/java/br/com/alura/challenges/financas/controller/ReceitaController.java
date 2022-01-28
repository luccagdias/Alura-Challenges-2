package br.com.alura.challenges.financas.controller;

import br.com.alura.challenges.financas.dto.request.ReceitaRequestDTO;
import br.com.alura.challenges.financas.dto.response.ReceitaResponseDTO;
import br.com.alura.challenges.financas.entity.Receita;
import br.com.alura.challenges.financas.service.ReceitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private ReceitaService service;

    public ReceitaController(ReceitaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaResponseDTO> findById(@PathVariable String id) {
        Receita receita = service.findById(id);

        return ResponseEntity.ok().body(ReceitaResponseDTO.toResponseDTO(receita));
    }

    @GetMapping("/{month}/{year}")
    public ResponseEntity<List<ReceitaResponseDTO>> findByMonth(@PathVariable String month, @PathVariable String year) {
        List<Receita> receitas = service.findByMonth(month, year);

        return ResponseEntity.ok().body(ReceitaResponseDTO.entityListToResponseDTOList(receitas));
    }

    @GetMapping
    public ResponseEntity<List<ReceitaResponseDTO>> findAll(@RequestParam(required = false) String descricao) {
        List<Receita> receitas =
                (descricao == null) ? service.findAll() : service.findAllByDescricao(descricao);

        return ResponseEntity.ok().body(ReceitaResponseDTO.entityListToResponseDTOList(receitas));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ReceitaRequestDTO receitaRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        Receita receita = ReceitaRequestDTO.toEntity(receitaRequestDTO);
        receita = service.save(receita);

        URI uri = uriComponentsBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaResponseDTO> update(@Valid @RequestBody ReceitaRequestDTO receitaRequestDTO, @PathVariable String id) {
        Receita receita = ReceitaRequestDTO.toEntity(receitaRequestDTO);
        receita = service.update(receita, id);

        return ResponseEntity.ok(ReceitaResponseDTO.toResponseDTO(receita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.ok("Receita excluída com sucesso");
    }
}
