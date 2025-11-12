package br.com.fiap.skill4green.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "execucoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Execucao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "colaborador_id", nullable = false)
  private Colaborador colaborador;

  @ManyToOne
  @JoinColumn(name = "tarefa_id", nullable = false)
  private Tarefa tarefa;

  @Column(nullable = false)
  private LocalDateTime data;

  @Column(nullable = false)
  private String resultado;
}