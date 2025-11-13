package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.exception.BusinessException;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.model.Colaborador;
import br.com.fiap.skill4green.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EcoCoinService {

  private final ColaboradorRepository repository;

  public int consultarSaldo(Long idColaborador) {
    Colaborador c = repository.findById(idColaborador)
      .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));
    return c.getEcoins();
  }

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