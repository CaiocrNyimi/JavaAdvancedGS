package br.com.fiap.skill4green.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankingResponse {
  private String setor;
  private int totalEcoins;
}