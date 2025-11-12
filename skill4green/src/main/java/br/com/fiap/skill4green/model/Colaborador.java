package br.com.fiap.skill4green.model;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "colaboradores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colaborador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String setor;

  @Enumerated(EnumType.STRING)
  @Column(name = "nivel_verde", nullable = false)
  private NivelVerde nivelVerde;

  @Column(nullable = false)
  private Integer ecoins;
}