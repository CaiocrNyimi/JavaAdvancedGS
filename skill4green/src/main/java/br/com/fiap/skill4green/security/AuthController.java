package br.com.fiap.skill4green.security;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authManager;
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
      try {
        authManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
    
        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(user);
    
        return ResponseEntity.ok(new AuthResponse(token));
      } catch (BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("erro", "Usuário inexistente ou senha inválida"));
      }
    }
}