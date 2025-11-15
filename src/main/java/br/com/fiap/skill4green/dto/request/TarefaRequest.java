package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.Dificuldade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TarefaRequest {

    @Schema(description = "Descrição da tarefa", example = "Apagar luzes desnecessárias")
    @NotBlank(message = "validacao.descricao.obrigatoria")
    private String descricao;

    @Schema(description = "Impacto em kWh economizados", example = "15.5")
    @Min(value = 0, message = "validacao.kwh.minimo")
    private double impactoKwh;

    @Schema(description = "Impacto em CO2 reduzido (kg)", example = "3.2")
    @Min(value = 0, message = "validacao.co2.minimo")
    private double impactoCo2;

    @Schema(description = "Dificuldade da tarefa (BAIXA|MEDIA|ALTA)", example = "BAIXA")
    @NotNull(message = "validacao.dificuldade.obrigatoria")
    private Dificuldade dificuldade;
}