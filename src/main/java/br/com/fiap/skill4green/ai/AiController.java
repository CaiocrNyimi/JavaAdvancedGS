package br.com.fiap.skill4green.ai;

import br.com.fiap.skill4green.ai.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@Tag(name = "AI Controller", description = "Endpoints de integração com o serviço Python FastAPI para recomendações e motivação")
public class AiController {

    private final PythonAiClient pythonAi;

    public AiController(PythonAiClient pythonAi) {
        this.pythonAi = pythonAi;
    }

    @Operation(
        summary = "Gerar recomendações sustentáveis",
        description = "Chama o serviço Python FastAPI para gerar recomendações de tarefas sustentáveis para o usuário.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = "application/json",
                examples = {
                    @ExampleObject(
                        name = "Exemplo de Requisição",
                        value = "{\n" +
                                "  \"user_summary\": {\n" +
                                "    \"user\": {\"id\":\"4c1f-123\",\"name\":\"Ana\"},\n" +
                                "    \"department\": \"TI\",\n" +
                                "    \"skills\": [\"eletrica_basica\",\"automacao\",\"interesse_aprender\"],\n" +
                                "    \"recent_tasks\": [\n" +
                                "      {\"task_code\":\"AC_OFF_AFTER_HOURS\",\"count\":5},\n" +
                                "      {\"task_code\":\"LED_REPLACE\",\"count\":2}\n" +
                                "    ],\n" +
                                "    \"goals\": {\"dept_kwh_reduction_pct\": 5}\n" +
                                "  },\n" +
                                "  \"max_items\": 4\n" +
                                "}"
                    )
                }
            )
        )
    )
    @ApiResponse(responseCode = "200", description = "Lista de recomendações gerada com sucesso")
    @PostMapping("/recommendations")
    public ResponseEntity<RecommendationResponse> recommendations(@RequestBody RecommendationsReq req) {
        RecommendationResponse resp = pythonAi.getRecommendations(req);
        return ResponseEntity.ok(resp);
    }

    @Operation(
        summary = "Gerar mensagem motivacional",
        description = "Chama o serviço Python FastAPI para gerar mensagem motivacional com base em uma tarefa executada.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = "application/json",
                examples = {
                    @ExampleObject(
                        name = "Exemplo de Requisição",
                        value = "{\n" +
                                "  \"task_code\": \"AC_OFF_AFTER_HOURS\",\n" +
                                "  \"executions\": 1,\n" +
                                "  \"name\": \"Ana\",\n" +
                                "  \"kwh\": 1.2,\n" +
                                "  \"co2\": 0.10,\n" +
                                "  \"cost\": 1.14\n" +
                                "}"
                    )
                }
            )
        )
    )
    @ApiResponse(responseCode = "200", description = "Mensagem motivacional gerada com sucesso")
    @PostMapping("/motivation")
    public ResponseEntity<MotivationResponse> motivation(@RequestBody MotivationReq req) {
        MotivationResponse resp = pythonAi.getMotivation(req);
        return ResponseEntity.ok(resp);
    }

    @Operation(
        summary = "Checar saúde do serviço Python",
        description = "Verifica se o serviço FastAPI está respondendo corretamente."
    )
    @ApiResponse(responseCode = "200", description = "Status OK do serviço Python")
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        String status = pythonAi.health();
        return ResponseEntity.ok(status);
    }
}