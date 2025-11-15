package br.com.fiap.skill4green.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @Schema(description = "Email do usuário para login", example = "admin@empresa.com")
    private String email;

    @Schema(description = "Senha do usuário para login", example = "admin123")
    private String senha;
}