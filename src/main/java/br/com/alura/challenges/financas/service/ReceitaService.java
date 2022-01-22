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
        List<Receita> receitas = findAllByDescricao(receita.getDescricao());
        for (Receita r : receitas) {
            if (receita.getData().getMonth().equals(r.getData().getMonth())) {
                return true;
            }
        }

        return false;
    }

    public List<Receita> findAllByDescricao(String descricao) {
        return repository.findAllByDescricaoIgnoreCase(descricao);
    }

    public Receita update(Receita receita, String id) {
        Receita receitaFromDatabase = findById(id);
        receita.setId(receitaFromDatabase.getId());

        boolean monthIsEqual = receita.getData().getMonth().equals(receitaFromDatabase.getData().getMonth());
        boolean descricaoIsEqual = receita.getDescricao().equals(receitaFromDatabase.getDescricao());

        // Quando se altera mês ou descrição da receita, faz verificação se receita já existe naquele mês
        // Esta verificação só é necessária se mês ou descrição estiver sendo alterado
        if (!monthIsEqual || !descricaoIsEqual) {
            return this.save(receita);
        }

        return repository.save(receita);
    }
}
