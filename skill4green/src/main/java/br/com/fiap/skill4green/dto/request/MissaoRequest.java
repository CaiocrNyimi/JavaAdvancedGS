package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.StatusMissao;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MissaoRequest {

  @NotBlank(message = "validacao.descricao.obrigatoria")
  private String descricao;

  @NotNull(message = "validacao.meta.obrigatoria")
  @DecimalMin(value = "0.0", inclusive = false, message = "validacao.meta.minima")
  private Double meta;

  @NotNull(message = "validacao.status.obrigatorio")
  private StatusMissao status;
}