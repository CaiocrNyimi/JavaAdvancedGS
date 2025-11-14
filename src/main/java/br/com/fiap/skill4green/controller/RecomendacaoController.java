package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.ai.CursoRecomendacaoService;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.repository.ColaboradorRepository;
import br.com.fiap.skill4green.repository.ExecucaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recomendacoes")
@RequiredArgsConstructor
public class RecomendacaoController {

  private final ExecucaoRepository execucaoRepository;
  private final ColaboradorRepository colaboradorRepository;
  private final CursoRecomendacaoService recomendacaoService;

  @GetMapping("/{idColaborador}")
  public Map<String, Object> recomendar(@PathVariable Long idColaborador) {
    colaboradorRepository.findById(idColaborador)
      .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));
  
    List<String> tarefasExecutadas = execucaoRepository.findByColaboradorId(idColaborador)
      .stream()
      .map(e -> e.getTarefa().getDescricao())
      .toList();
  
    String resposta = recomendacaoService.recomendarCursos(tarefasExecutadas);
  
    return Map.of(
      "idColaborador", idColaborador,
      "tarefas", tarefasExecutadas,
      "recomendacoes", resposta
    );
  }
}