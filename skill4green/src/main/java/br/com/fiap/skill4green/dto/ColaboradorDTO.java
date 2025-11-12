package br.com.fiap.skill4green.dto;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColaboradorDTO {

  private Long id;

  @NotBlank
  private String nome;

  @NotBlank
  private String setor;

  @NotNull
  private NivelVerde nivelVerde;

  @Min(0)
  private Integer ecoins;
}