package br.com.fiap.skill4green.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecucaoDTO {

  private Long id;

  @NotNull
  private Long idColaborador;

  @NotNull
  private Long idTarefa;

  @NotNull
  private LocalDateTime data;

  @NotBlank
  private String resultado;
}