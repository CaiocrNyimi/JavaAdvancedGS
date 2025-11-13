package br.com.fiap.skill4green.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

  @Email(message = "E-mail inválido")
  @NotBlank(message = "O e-mail é obrigatório")
  private String email;

  @NotBlank(message = "A senha é obrigatória")
  private String senha;
}