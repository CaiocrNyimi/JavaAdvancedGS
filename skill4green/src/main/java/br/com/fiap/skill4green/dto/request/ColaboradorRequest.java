package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import br.com.fiap.skill4green.model.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColaboradorRequest {

  @NotBlank(message = "validacao.nome.obrigatorio")
  private String nome;

  @NotBlank(message = "validacao.setor.obrigatorio")
  private String setor;

  @Email(message = "validacao.email.invalido")
  @NotBlank(message = "validacao.email.obrigatorio")
  private String email;

  @NotBlank(message = "validacao.senha.obrigatoria")
  private String senha;

  @NotNull(message = "validacao.perfil.obrigatorio")
  private Perfil perfil;

  @NotNull(message = "validacao.nivel.obrigatorio")
  private NivelVerde nivelVerde;

  @NotNull(message = "validacao.ecoins.obrigatorio")
  @Min(value = 0, message = "validacao.ecoins.minimo")
  private Integer ecoins;
}