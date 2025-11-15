package br.com.fiap.skill4green.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Schema(description = "Email do usuário", example = "admin@empresa.com")
    @Email(message = "validacao.email.invalido")
    @NotBlank(message = "validacao.email.obrigatorio")
    private String email;

    @Schema(description = "Senha do usuário", example = "admin123")
    @NotBlank(message = "validacao.senha.obrigatoria")
    private String senha;
}