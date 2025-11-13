package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.Dificuldade;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class TarefaRequest {

  @NotBlank(message = "validacao.descricao.obrigatoria")
  private String descricao;

  @Min(value = 0, message = "validacao.kwh.minimo")
  private double impactoKwh;

  @Min(value = 0, message = "validacao.co2.minimo")
  private double impactoCo2;

  @NotNull(message = "validacao.dificuldade.obrigatoria")
  private Dificuldade dificuldade;
}