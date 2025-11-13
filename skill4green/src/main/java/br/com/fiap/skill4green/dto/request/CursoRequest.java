package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class CursoRequest {

  @NotBlank(message = "validacao.tema.obrigatorio")
  private String tema;

  @NotNull(message = "validacao.nivel.obrigatorio")
  private NivelVerde nivel;

  @NotBlank(message = "validacao.link.obrigatorio")
  private String link;
}