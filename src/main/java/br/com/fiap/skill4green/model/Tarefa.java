package br.com.fiap.skill4green.model;

import br.com.fiap.skill4green.model.enums.Dificuldade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tarefas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String descricao;

  @Column(name = "impacto_kwh", nullable = false)
  private Double impactoKwh;

  @Column(name = "impacto_co2", nullable = false)
  private Double impactoCo2;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Dificuldade dificuldade;
}