package br.com.fiap.skill4green.security;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import br.com.fiap.skill4green.security.AuthRequest;
import br.com.fiap.skill4green.security.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(
    name = "Auth",
    description = "Autenticação e geração de JWT",
    extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "1"))
)
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Operation(
        summary = "Login",
        description = "Autentica um usuário com email e senha e retorna um token JWT"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{ \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\" }")
            )
        ),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{ \"erro\": \"Usuário inexistente ou senha inválida\" }")
            )
        )
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Credenciais de login",
            required = true,
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{ \"email\": \"admin@empresa.com\", \"senha\": \"admin123\" }")
            )
        )
        @RequestBody AuthRequest request) {
        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
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