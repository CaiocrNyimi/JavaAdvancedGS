package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.Dificuldade;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class TarefaRequest {

  @NotBlank
  private String descricao;

  @Min(0)
  private double impactoKwh;

  @Min(0)
  private double impactoCo2;

  @NotNull
  private Dificuldade dificuldade;
}