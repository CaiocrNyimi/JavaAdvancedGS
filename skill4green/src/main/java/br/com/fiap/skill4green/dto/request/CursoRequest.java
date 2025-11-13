package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class CursoRequest {

  @NotBlank
  private String tema;

  @NotNull
  private NivelVerde nivel;

  @NotBlank
  private String link;
}