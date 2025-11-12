package br.com.fiap.skill4green.dto;

import br.com.fiap.skill4green.model.enums.StatusMissao;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissaoDTO {

  private Long id;

  @NotBlank
  private String descricao;

  @NotNull
  @DecimalMin("0.0")
  private Double meta;

  @NotNull
  private StatusMissao status;
}