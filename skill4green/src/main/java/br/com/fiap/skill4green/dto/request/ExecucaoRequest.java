package br.com.fiap.skill4green.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExecucaoRequest {

  @NotNull
  private Long idColaborador;

  @NotNull
  private Long idTarefa;

  @NotNull
  private LocalDateTime data;

  @NotBlank
  private String resultado;
}