package br.com.alura.challenges.financas.repository;

import br.com.alura.challenges.financas.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Receita findByDescricaoIgnoreCase(String descricao);
}
