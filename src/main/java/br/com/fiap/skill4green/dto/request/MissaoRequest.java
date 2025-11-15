package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.StatusMissao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MissaoRequest {

    @Schema(description = "Descrição da missão", example = "Reduzir consumo de energia em 10%")
    @NotBlank(message = "validacao.descricao.obrigatoria")
    private String descricao;

    @Schema(description = "Meta da missão", example = "10.0")
    @NotNull(message = "validacao.meta.obrigatoria")
    @DecimalMin(value = "0.0", inclusive = false, message = "validacao.meta.minima")
    private Double meta;

    @Schema(description = "Status da missão (PENDENTE, EM_ANDAMENTO ou CONCLUIDA)", example = "EM_ANDAMENTO")
    @NotNull(message = "validacao.status.obrigatorio")
    private StatusMissao status;
}