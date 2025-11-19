package br.com.fiap.skill4green.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final UserDetailsService userDetailsService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .cors(cors -> {})
        .authorizeHttpRequests(auth -> auth
            // Endpoints públicos
            .requestMatchers(
                "/auth/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/api-docs/**",
                "/ai/**"
            ).permitAll()
            .requestMatchers(HttpMethod.POST, "/colaboradores").permitAll()

            // USER pode ver tudo
            .requestMatchers(HttpMethod.GET, "/colaboradores/**").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.GET, "/cursos/**").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.GET, "/missoes/**").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.GET, "/tarefas/**").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.GET, "/execucoes/**").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.GET, "/ranking/**").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.GET, "/recomendacoes/**").hasAnyRole("USER","ADMIN")

            // USER também pode registrar execuções e converter ecoins
            .requestMatchers(HttpMethod.POST, "/execucoes/**").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.POST, "/ecoins/**").hasAnyRole("USER","ADMIN")

            // ADMIN pode tudo
            .anyRequest().hasRole("ADMIN")
        )
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(List.of("*"));
    configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(authProvider);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}