package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.LoginRequest;
import br.com.fiap.skill4green.dto.response.LoginResponse;
import br.com.fiap.skill4green.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authManager;
  private final JwtService jwtService;

  public LoginResponse autenticar(LoginRequest request) {
    Authentication authentication = authManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
    );

    String token = jwtService.generateToken((org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal());
    return LoginResponse.builder().token(token).build();
  }
}