package br.com.fiap.skill4green.dto.response;

import br.com.fiap.skill4green.model.enums.StatusMissao;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MissaoResponse {
  private Long id;
  private String descricao;
  private Double meta;
  private StatusMissao status;
}