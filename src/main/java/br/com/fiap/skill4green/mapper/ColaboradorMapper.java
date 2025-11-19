package br.com.fiap.skill4green.mapper;

import br.com.fiap.skill4green.dto.request.ColaboradorRequest;
import br.com.fiap.skill4green.dto.response.ColaboradorResponse;
import br.com.fiap.skill4green.model.Colaborador;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ColaboradorMapper {

  private final PasswordEncoder encoder;

  public Colaborador toEntity(ColaboradorRequest request) {
    return Colaborador.builder()
      .nome(request.getNome())
      .email(request.getEmail())
      .senha(encoder.encode(request.getSenha()))
      .perfil(request.getPerfil())
      .setor(request.getSetor())
      .nivelVerde(request.getNivelVerde())
      .ecoins(request.getEcoins())
      .build();
  }

  public ColaboradorResponse toResponse(Colaborador entity) {
    return ColaboradorResponse.builder()
      .id(entity.getId())
      .nome(entity.getNome())
      .email(entity.getEmail())
      .perfil(entity.getPerfil())
      .setor(entity.getSetor())
      .nivelVerde(entity.getNivelVerde())
      .ecoins(entity.getEcoins())
      .build();
  }
}