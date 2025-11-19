package br.com.fiap.skill4green.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Perfil implements GrantedAuthority {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER");

  private final String role;

  @Override
  public String getAuthority() {
    return role;
  }
}