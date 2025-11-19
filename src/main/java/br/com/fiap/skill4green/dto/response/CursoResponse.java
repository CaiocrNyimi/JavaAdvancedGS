package br.com.fiap.skill4green.dto.response;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoResponse {

    @Schema(description = "ID do curso", example = "10")
    private Long id;

    @Schema(description = "Tema do curso", example = "Eficiência Energética")
    private String tema;

    @Schema(description = "Nível verde do curso (INICIANTE, EXPLORADOR, GUARDIAO ou MENTOR)", example = "INICIANTE")
    private NivelVerde nivel;

    @Schema(description = "Link para acesso ao curso", example = "https://cursos.com/eficiencia-energetica")
    private String link;
}