package br.com.fiap.skill4green.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankingResponse {

    @Schema(description = "Setor avaliado", example = "TI")
    private String setor;

    @Schema(description = "Total de ecoins acumulados pelo setor", example = "500")
    private int totalEcoins;
}