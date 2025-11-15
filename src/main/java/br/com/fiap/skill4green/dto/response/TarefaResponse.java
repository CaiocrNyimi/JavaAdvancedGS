package br.com.fiap.skill4green.dto.response;

import br.com.fiap.skill4green.model.enums.Dificuldade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TarefaResponse {

    @Schema(description = "ID da tarefa", example = "20")
    private Long id;

    @Schema(description = "Descrição da tarefa", example = "Apagar luzes desnecessárias")
    private String descricao;

    @Schema(description = "Impacto em kWh economizados", example = "15.5")
    private double impactoKwh;

    @Schema(description = "Impacto em CO2 reduzido (kg)", example = "3.2")
    private double impactoCo2;

    @Schema(description = "Dificuldade da tarefa (BAIXA|MEDIA|ALTA)", example = "BAIXA")
    private Dificuldade dificuldade;
}