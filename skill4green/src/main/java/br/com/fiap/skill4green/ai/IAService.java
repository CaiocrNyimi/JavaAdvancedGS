package br.com.fiap.skill4green.ai;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IAService {

    private final OpenAiService openAiService;

    public IAService(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    public String recomendarTrilha(String nome, String setor, String nivelVerde) {
        String prompt = String.format("""
            Você é um assistente de sustentabilidade. Com base nas informações abaixo, recomende uma trilha de aprendizado personalizada com foco em ESG (ambiental, social e governança):

            Nome: %s
            Setor: %s
            Nível Verde: %s

            A trilha deve conter temas práticos, relevantes e com impacto positivo. Liste os tópicos sugeridos.
            """, nome, setor, nivelVerde);

        CompletionRequest request = CompletionRequest.builder()
                .prompt(prompt)
                .model("text-davinci-003")
                .maxTokens(300)
                .temperature(0.7)
                .build();

        CompletionResult result = openAiService.createCompletion(request);
        return result.getChoices().get(0).getText().trim();
    }
}