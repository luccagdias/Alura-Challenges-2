package br.com.alura.challenges.financas.repository;

import br.com.alura.challenges.financas.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Despesa> findAllByDescricaoIgnoreCase(String descricao);
}
