package br.com.fiap.skill4green.dto;

import br.com.fiap.skill4green.model.enums.Dificuldade;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaDTO {

  private Long id;

  @NotBlank
  private String descricao;

  @NotNull
  @DecimalMin("0.0")
  private Double impactoKwh;

  @NotNull
  @DecimalMin("0.0")
  private Double impactoCo2;

  @NotNull
  private Dificuldade dificuldade;
}