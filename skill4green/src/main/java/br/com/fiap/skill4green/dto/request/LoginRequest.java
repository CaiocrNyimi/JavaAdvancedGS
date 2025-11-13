package br.com.fiap.skill4green.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

  @Email(message = "validacao.email.invalido")
  @NotBlank(message = "validacao.email.obrigatorio")
  private String email;

  @NotBlank(message = "validacao.senha.obrigatoria")
  private String senha;
}