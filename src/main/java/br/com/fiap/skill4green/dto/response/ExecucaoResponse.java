package br.com.fiap.skill4green.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

@Data
@Builder
public class ExecucaoResponse implements Serializable {
  private Long id;
  private Long idColaborador;
  private Long idTarefa;
  private LocalDateTime data;
  private String resultado;
}