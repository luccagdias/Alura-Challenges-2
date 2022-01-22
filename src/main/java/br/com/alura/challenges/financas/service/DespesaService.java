package br.com.alura.challenges.financas.service;

import br.com.alura.challenges.financas.entity.Despesa;
import br.com.alura.challenges.financas.exception.AlreadyRegisteredThisMonthException;
import br.com.alura.challenges.financas.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DespesaService {

    private DespesaRepository repository;

    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }

    public Despesa findById(String id) {
        Despesa despesa = repository.findById(Long.valueOf(id)).orElse(null);
        if (despesa == null) {
            throw new NoSuchElementException("Não foi encontrado nenhum item para o id indicado");
        }

        return despesa;
    }

    public List<Despesa> findAll() {
        return repository.findAll();
    }

    public Despesa save(Despesa despesa) {
        if (alreadyRegisteredThisMonth(despesa)) {
            throw new AlreadyRegisteredThisMonthException("Uma despesa com a mesma descrição já foi cadastrada no mês indicado");
        }

        return repository.save(despesa);
    }

    public boolean alreadyRegisteredThisMonth(Despesa despesa) {
        List<Despesa> despesas = findAllByDescricao(despesa.getDescricao());
        for (Despesa d : despesas) {
            if (despesa.getData().getMonth().equals(d.getData().getMonth())) {
                return true;
            }
        }

        return false;
    }

    public List<Despesa> findAllByDescricao(String descricao) {
        return repository.findAllByDescricaoIgnoreCase(descricao);
    }

    public Despesa update(Despesa despesa, String id) {
        Despesa despesaFromDatabase = findById(id);
        despesa.setId(despesaFromDatabase.getId());

        boolean monthIsEqual = despesa.getData().getMonth().equals(despesaFromDatabase.getData().getMonth());
        boolean descricaoIsEqual = despesa.getDescricao().equals(despesaFromDatabase.getDescricao());

        // Quando se altera mês ou descrição da despesa, faz verificação se despesa já existe naquele mês
        // Esta verificação só é necessária se mês ou descrição estiver sendo alterado
        if (!monthIsEqual || !descricaoIsEqual) {
            return this.save(despesa);
        }

        return repository.save(despesa);
    }

    public void delete(String id) {
        Despesa despesa = findById(id);

        repository.delete(despesa);
    }
}
