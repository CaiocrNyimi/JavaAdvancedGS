package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import br.com.fiap.skill4green.model.enums.Perfil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColaboradorRequest {

    @Schema(description = "Nome completo do colaborador", example = "Admin Silva")
    @NotBlank(message = "validacao.nome.obrigatorio")
    private String nome;

    @Schema(description = "Setor de atuação", example = "TI")
    @NotBlank(message = "validacao.setor.obrigatorio")
    private String setor;

    @Schema(description = "Email corporativo", example = "admin@empresa.com")
    @Email(message = "validacao.email.invalido")
    @NotBlank(message = "validacao.email.obrigatorio")
    private String email;

    @Schema(description = "Senha de acesso", example = "admin123")
    @NotBlank(message = "validacao.senha.obrigatoria")
    private String senha;

    @Schema(description = "Perfil de acesso (USER ou ADMIN)", example = "ADMIN")
    @NotNull(message = "validacao.perfil.obrigatorio")
    private Perfil perfil;

    @Schema(description = "Nível verde do colaborador (INICIANTE, EXPLORADOR, GUARDIAO ou MENTOR)", example = "MENTOR")
    @NotNull(message = "validacao.nivel.obrigatorio")
    private NivelVerde nivelVerde;

    @Schema(description = "Quantidade de ecoins disponíveis", example = "10000")
    @NotNull(message = "validacao.ecoins.obrigatorio")
    @Min(value = 0, message = "validacao.ecoins.minimo")
    private Integer ecoins;
}