package br.com.fiap.skill4green.repository;

import br.com.fiap.skill4green.model.Execucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecucaoRepository extends JpaRepository<Execucao, Long> {
}