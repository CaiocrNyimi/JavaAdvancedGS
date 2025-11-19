package br.com.fiap.skill4green.ai;

import br.com.fiap.skill4green.ai.dto.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PythonAiClient {

    private final WebClient webClient;

    public PythonAiClient(WebClient pythonWebClient) {
        this.webClient = pythonWebClient;
    }

    public RecommendationResponse getRecommendations(RecommendationsReq req) {
        return webClient.post()
            .uri("/ai/recommendations")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(req)
            .retrieve()
            .bodyToMono(RecommendationResponse.class)
            .block();
    }

    public MotivationResponse getMotivation(MotivationReq req) {
        return webClient.post()
            .uri("/ai/motivation")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(req)
            .retrieve()
            .bodyToMono(MotivationResponse.class)
            .block();
    }

    public String health() {
        return webClient.get()
            .uri("/health")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}