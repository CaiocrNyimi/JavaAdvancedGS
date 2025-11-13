package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.StatusMissao;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MissaoRequest {

  @NotBlank
  private String descricao;

  @NotNull
  @DecimalMin(value = "0.0", inclusive = false)
  private Double meta;

  @NotNull
  private StatusMissao status;
}