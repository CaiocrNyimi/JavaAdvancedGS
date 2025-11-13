package br.com.fiap.skill4green.dto.request;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import br.com.fiap.skill4green.model.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColaboradorRequest {

  @NotBlank(message = "O nome é obrigatório")
  private String nome;

  @NotBlank(message = "O setor é obrigatório")
  private String setor;

  @Email(message = "E-mail inválido")
  @NotBlank(message = "O e-mail é obrigatório")
  private String email;

  @NotBlank(message = "A senha é obrigatória")
  private String senha;

  @NotNull(message = "O perfil é obrigatório")
  private Perfil perfil;

  @NotNull(message = "O nível verde é obrigatório")
  private NivelVerde nivelVerde;

  @NotNull(message = "Os ecoins são obrigatórios")
  private Integer ecoins;
}