package br.com.alura.challenges.financas.controller;

import br.com.alura.challenges.financas.dto.request.ReceitaRequestDTO;
import br.com.alura.challenges.financas.entity.Receita;
import br.com.alura.challenges.financas.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private ReceitaService service;

    public ReceitaController(ReceitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ReceitaRequestDTO receitaRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        Receita receita = ReceitaRequestDTO.toEntity(receitaRequestDTO);
        receita = service.save(receita);

        URI uri = uriComponentsBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
