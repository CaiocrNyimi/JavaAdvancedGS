package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.response.RankingResponse;
import br.com.fiap.skill4green.model.Colaborador;
import br.com.fiap.skill4green.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {

  private final ColaboradorRepository repository;

  @Cacheable("ranking")
  public List<RankingResponse> consultarRanking() {
    Map<String, Integer> ecoinsPorSetor = repository.findAll().stream()
      .collect(Collectors.groupingBy(
        Colaborador::getSetor,
        Collectors.summingInt(Colaborador::getEcoins)
      ));

    return ecoinsPorSetor.entrySet().stream()
      .map(entry -> new RankingResponse(entry.getKey(), entry.getValue()))
      .sorted(Comparator.comparingInt(RankingResponse::getTotalEcoins).reversed())
      .collect(Collectors.toList());
  }
}