package br.com.fiap.skill4green.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExecucaoRequest {

    @Schema(description = "ID do colaborador que executou a tarefa", example = "1")
    @NotNull(message = "validacao.colaborador.obrigatorio")
    private Long idColaborador;

    @Schema(description = "ID da tarefa executada", example = "2")
    @NotNull(message = "validacao.tarefa.obrigatoria")
    private Long idTarefa;

    @Schema(description = "Data e hora da execução", example = "2025-11-14T22:30:00")
    @NotNull(message = "validacao.data.obrigatoria")
    private LocalDateTime data;

    @Schema(description = "Resultado da execução", example = "Economia de energia")
    @NotBlank(message = "validacao.resultado.obrigatorio")
    private String resultado;
}