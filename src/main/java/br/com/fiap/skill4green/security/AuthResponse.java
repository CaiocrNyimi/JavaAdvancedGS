package br.com.fiap.skill4green.security;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
  private String token;
}