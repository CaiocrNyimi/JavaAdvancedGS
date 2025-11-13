package br.com.fiap.skill4green.mapper;

import br.com.fiap.skill4green.dto.request.CursoRequest;
import br.com.fiap.skill4green.dto.response.CursoResponse;
import br.com.fiap.skill4green.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

  public Curso toEntity(CursoRequest request) {
    return Curso.builder()
      .tema(request.getTema())
      .nivel(request.getNivel())
      .link(request.getLink())
      .build();
  }

  public CursoResponse toResponse(Curso entity) {
    return CursoResponse.builder()
      .id(entity.getId())
      .tema(entity.getTema())
      .nivel(entity.getNivel())
      .link(entity.getLink())
      .build();
  }
}