package br.com.fiap.skill4green.model;

import br.com.fiap.skill4green.model.enums.StatusMissao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Missao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String descricao;

  @Column(nullable = false)
  private Double meta;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StatusMissao status;
}