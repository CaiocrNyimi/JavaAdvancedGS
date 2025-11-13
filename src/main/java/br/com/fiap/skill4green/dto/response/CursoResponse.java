package br.com.fiap.skill4green.dto.response;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoResponse {
  private Long id;
  private String tema;
  private NivelVerde nivel;
  private String link;
}