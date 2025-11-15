package br.com.fiap.skill4green.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecucaoResponse implements Serializable {

    @Schema(description = "ID da execução", example = "100")
    private Long id;

    @Schema(description = "ID do colaborador que executou a tarefa", example = "1")
    private Long idColaborador;

    @Schema(description = "ID da tarefa executada", example = "2")
    private Long idTarefa;

    @Schema(description = "Data e hora da execução", example = "2025-11-14T22:30:00")
    private LocalDateTime data;

    @Schema(description = "Resultado da execução", example = "Economia de energia")
    private String resultado;
}