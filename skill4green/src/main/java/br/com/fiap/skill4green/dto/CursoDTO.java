package br.com.fiap.skill4green.dto;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoDTO {

  private Long id;

  @NotBlank
  private String tema;

  @NotNull
  private NivelVerde nivel;

  @NotBlank
  @Pattern(regexp = "^(http|https)://.*$", message = "Link deve ser uma URL válida")
  private String link;
}