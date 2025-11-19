package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.exception.BusinessException;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.model.Colaborador;
import br.com.fiap.skill4green.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

@Service
@RequiredArgsConstructor
public class EcoCoinService {

  private final ColaboradorRepository repository;

  @Cacheable(value = "ecoins", key = "#idColaborador")
  public int consultarSaldo(Long idColaborador) {
    Colaborador c = repository.findById(idColaborador)
      .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));
    return c.getEcoins();
  }

  @CacheEvict(value = "ecoins", key = "#idColaborador")
  public void converterEcoCoins(Long idColaborador, int quantidade, String tipoRecompensa) {
    Colaborador c = repository.findById(idColaborador)
      .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));

    if (c.getEcoins() < quantidade) {
      throw new BusinessException("erro.ecoins.insuficientes");
    }

    c.setEcoins(c.getEcoins() - quantidade);
    repository.save(c);
  }
}