package br.com.fiap.skill4green.dto.response;

import br.com.fiap.skill4green.model.enums.StatusMissao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MissaoResponse {

    @Schema(description = "ID da missão", example = "5")
    private Long id;

    @Schema(description = "Descrição da missão", example = "Reduzir consumo de energia em 10%")
    private String descricao;

    @Schema(description = "Meta da missão", example = "10.0")
    private Double meta;

    @Schema(description = "Status da missão (PENDENTE, EM_ANDAMENTO ou CONCLUIDA)", example = "EM_ANDAMENTO")
    private StatusMissao status;
}