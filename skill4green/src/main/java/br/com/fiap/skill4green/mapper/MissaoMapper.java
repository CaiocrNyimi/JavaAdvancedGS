package br.com.fiap.skill4green.mapper;

import br.com.fiap.skill4green.dto.request.MissaoRequest;
import br.com.fiap.skill4green.dto.response.MissaoResponse;
import br.com.fiap.skill4green.model.Missao;
import org.springframework.stereotype.Component;

@Component
public class MissaoMapper {

  public Missao toEntity(MissaoRequest request) {
    return Missao.builder()
      .descricao(request.getDescricao())
      .meta(request.getMeta())
      .status(request.getStatus())
      .build();
  }

  public MissaoResponse toResponse(Missao entity) {
    return MissaoResponse.builder()
      .id(entity.getId())
      .descricao(entity.getDescricao())
      .meta(entity.getMeta())
      .status(entity.getStatus())
      .build();
  }
}