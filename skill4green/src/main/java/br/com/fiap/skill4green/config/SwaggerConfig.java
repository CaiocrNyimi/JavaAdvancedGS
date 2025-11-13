package br.com.fiap.skill4green.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI skill4greenOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("Skill4Green API")
        .version("1.0")
        .description("API REST para promover sustentabilidade corporativa com gamificação e aprendizado")
        .contact(new Contact()
          .name("Equipe Skill4Green")
          .email("contato@skill4green.com")
        )
      );
  }
}