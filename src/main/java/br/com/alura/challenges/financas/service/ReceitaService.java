package br.com.alura.challenges.financas.service;

import br.com.alura.challenges.financas.entity.Receita;
import br.com.alura.challenges.financas.exception.ReceitaAlreadyRegisteredThisMonthException;
import br.com.alura.challenges.financas.repository.ReceitaRepository;
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
            throw new NoSuchElementException("Não foi encontrado nenhum item para o id indicado");
        }

        return receita;
    }

    public List<Receita> findAll() {
        return repository.findAll();
    }

    public Receita save(Receita receita) {
        if (alreadyRegisteredThisMonth(receita)) {
            throw new ReceitaAlreadyRegisteredThisMonthException();
        }

        return repository.save(receita);
    }

    public boolean alreadyRegisteredThisMonth(Receita receita) {
        Receita receitaAux = findByDescricao(receita.getDescricao());
        if (receitaAux != null) {
            return receita.getData().getMonth().equals(receitaAux.getData().getMonth());
        }

        return false;
    }

    public Receita findByDescricao(String descricao) {
        return repository.findByDescricaoIgnoreCase(descricao);
    }
}
