package br.com.fiap.skill4green.mapper;

import br.com.fiap.skill4green.dto.request.ExecucaoRequest;
import br.com.fiap.skill4green.dto.response.ExecucaoResponse;
import br.com.fiap.skill4green.model.Colaborador;
import br.com.fiap.skill4green.model.Execucao;
import br.com.fiap.skill4green.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class ExecucaoMapper {

  public Execucao toEntity(ExecucaoRequest request, Colaborador colaborador, Tarefa tarefa) {
    return Execucao.builder()
      .colaborador(colaborador)
      .tarefa(tarefa)
      .data(request.getData())
      .resultado(request.getResultado())
      .build();
  }

  public ExecucaoResponse toResponse(Execucao entity) {
    return ExecucaoResponse.builder()
      .id(entity.getId())
      .idColaborador(entity.getColaborador().getId())
      .idTarefa(entity.getTarefa().getId())
      .data(entity.getData())
      .resultado(entity.getResultado())
      .build();
  }
}