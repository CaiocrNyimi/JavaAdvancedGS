package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CursoRequest {

    @Schema(description = "Tema do curso", example = "Eficiência Energética")
    @NotBlank(message = "validacao.tema.obrigatorio")
    private String tema;

    @Schema(description = "Nível verde do curso (INICIANTE, EXPLORADOR, GUARDIAO ou MENTOR)", example = "INICIANTE")
    @NotNull(message = "validacao.nivel.obrigatorio")
    private NivelVerde nivel;

    @Schema(description = "Link para acesso ao curso", example = "https://cursos.com/eficiencia-energetica")
    @NotBlank(message = "validacao.link.obrigatorio")
    private String link;
}