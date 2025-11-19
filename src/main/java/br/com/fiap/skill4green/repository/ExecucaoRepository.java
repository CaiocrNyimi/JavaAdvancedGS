package br.com.fiap.skill4green.repository;

import br.com.fiap.skill4green.model.Execucao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExecucaoRepository extends JpaRepository<Execucao, Long> {
  List<Execucao> findByColaboradorId(Long idColaborador);
}