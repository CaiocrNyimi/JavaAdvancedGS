package br.com.fiap.skill4green.model;

import br.com.fiap.skill4green.model.enums.NivelVerde;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String tema;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private NivelVerde nivel;

  @Column(nullable = false)
  private String link;
}