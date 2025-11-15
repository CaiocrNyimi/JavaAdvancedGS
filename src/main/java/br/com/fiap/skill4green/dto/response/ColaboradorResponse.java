package br.com.fiap.skill4green.dto.response;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import br.com.fiap.skill4green.model.enums.Perfil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColaboradorResponse {

    @Schema(description = "ID do colaborador", example = "1")
    private Long id;

    @Schema(description = "Nome completo do colaborador", example = "Admin Silva")
    private String nome;

    @Schema(description = "Setor de atuação", example = "TI")
    private String setor;

    @Schema(description = "Email corporativo", example = "admin@empresa.com")
    private String email;

    @Schema(description = "Perfil de acesso (USER ou ADMIN)", example = "USER")
    private Perfil perfil;

    @Schema(description = "Nível verde do colaborador (INICIANTE, EXPLORADOR, GUARDIAO ou MENTOR)", example = "MENTOR")
    private NivelVerde nivelVerde;

    @Schema(description = "Quantidade de ecoins disponíveis", example = "10000")
    private Integer ecoins;
}