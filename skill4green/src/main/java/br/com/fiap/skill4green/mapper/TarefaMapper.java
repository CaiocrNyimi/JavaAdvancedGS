package br.com.fiap.skill4green.mapper;

import br.com.fiap.skill4green.dto.request.TarefaRequest;
import br.com.fiap.skill4green.dto.response.TarefaResponse;
import br.com.fiap.skill4green.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

  public Tarefa toEntity(TarefaRequest request) {
    return Tarefa.builder()
      .descricao(request.getDescricao())
      .impactoKwh(request.getImpactoKwh())
      .impactoCo2(request.getImpactoCo2())
      .dificuldade(request.getDificuldade())
      .build();
  }

  public TarefaResponse toResponse(Tarefa entity) {
    return TarefaResponse.builder()
      .id(entity.getId())
      .descricao(entity.getDescricao())
      .impactoKwh(entity.getImpactoKwh())
      .impactoCo2(entity.getImpactoCo2())
      .dificuldade(entity.getDificuldade())
      .build();
  }
}