package br.com.fiap.skill4green.security;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
  private String email;
  private String password;
}