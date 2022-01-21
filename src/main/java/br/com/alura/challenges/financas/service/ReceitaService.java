package br.com.alura.challenges.financas.service;

import br.com.alura.challenges.financas.entity.Receita;
import br.com.alura.challenges.financas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReceitaService {

    private ReceitaRepository repository;

    public ReceitaService(ReceitaRepository repository) {
        this.repository = repository;
    }

    public Receita findById(String id) {
        Receita receita = repository.findById(Long.valueOf(id)).orElse(null);
        if (receita == null) {
            throw new NoSuchElementException();
        }

        return receita;
    }

    public List<Receita> findAll() {
        return repository.findAll();
    }

    public Receita save(Receita receita) {
        return repository.save(receita);
    }
}
