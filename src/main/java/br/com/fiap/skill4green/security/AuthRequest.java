package br.com.fiap.skill4green.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
  private String email;

  @JsonProperty("senha")
  private String password;
}