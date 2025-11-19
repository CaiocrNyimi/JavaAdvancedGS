package br.com.fiap.skill4green.repository;

import br.com.fiap.skill4green.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
  Optional<Colaborador> findByEmail(String email);
}