package br.com.alura.challenges.financas.service;

import br.com.alura.challenges.financas.entity.Receita;
import br.com.alura.challenges.financas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    private ReceitaRepository repository;

    public ReceitaService(ReceitaRepository repository) {
        this.repository = repository;
    }

    public Receita save(Receita receita) {
        return repository.save(receita);
    }

    public List<Receita> findAll() {
        return repository.findAll();
    }
}
