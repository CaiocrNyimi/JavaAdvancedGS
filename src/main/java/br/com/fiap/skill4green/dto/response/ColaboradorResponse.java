package br.com.fiap.skill4green.dto.response;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import br.com.fiap.skill4green.model.enums.Perfil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColaboradorResponse {

  private Long id;
  private String nome;
  private String setor;
  private String email;
  private Perfil perfil;
  private NivelVerde nivelVerde;
  private Integer ecoins;
}