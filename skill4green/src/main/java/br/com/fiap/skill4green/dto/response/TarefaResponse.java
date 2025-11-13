package br.com.fiap.skill4green.dto.response;

import br.com.fiap.skill4green.model.enums.Dificuldade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TarefaResponse {
  private Long id;
  private String descricao;
  private double impactoKwh;
  private double impactoCo2;
  private Dificuldade dificuldade;
}