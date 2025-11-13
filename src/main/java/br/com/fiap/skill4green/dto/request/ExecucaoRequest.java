package br.com.fiap.skill4green.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExecucaoRequest {

  @NotNull(message = "validacao.colaborador.obrigatorio")
  private Long idColaborador;

  @NotNull(message = "validacao.tarefa.obrigatoria")
  private Long idTarefa;

  @NotNull(message = "validacao.data.obrigatoria")
  private LocalDateTime data;

  @NotBlank(message = "validacao.resultado.obrigatorio")
  private String resultado;
}