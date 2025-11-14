package br.com.fiap.skill4green.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoRecomendacaoService {

  private final ChatClient chatClient;

  public String recomendarCursos(List<String> tarefasExecutadas) {
    if (tarefasExecutadas == null || tarefasExecutadas.isEmpty()) {
      return "Nenhuma tarefa executada foi informada para gerar recomendações.";
    }

    String prompt = gerarPrompt(tarefasExecutadas);

    return chatClient
      .prompt()
      .user(prompt)
      .call()
      .content();
  }

  private String gerarPrompt(List<String> tarefas) {
    StringBuilder sb = new StringBuilder();
    sb.append("Sou um colaborador que realizou as seguintes tarefas sustentáveis:\n");
    tarefas.forEach(t -> sb.append("- ").append(t).append("\n"));
    sb.append("Com base nisso, recomende cursos curtos e práticos sobre sustentabilidade, eficiência energética ou ESG.");
    return sb.toString();
  }
}