package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.response.RankingResponse;
import br.com.fiap.skill4green.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

  private final RankingService service;

  @GetMapping
  public List<RankingResponse> consultar() {
    return service.consultarRanking();
  }
}