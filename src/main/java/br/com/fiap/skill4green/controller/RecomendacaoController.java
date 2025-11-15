package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.ai.CursoRecomendacaoService;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.repository.ColaboradorRepository;
import br.com.fiap.skill4green.repository.ExecucaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(
    name = "Recomendações",
    description = "Recomendações de cursos via IA",
    extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "9"))
)
@RestController
@RequestMapping("/recomendacoes")
@RequiredArgsConstructor
public class RecomendacaoController {

    private final ExecucaoRepository execucaoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final CursoRecomendacaoService recomendacaoService;

    @Operation(summary = "Gerar recomendações de cursos", description = "Gera recomendações de cursos sustentáveis via IA com base nas tarefas executadas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recomendações geradas com sucesso",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{ \"idColaborador\": 1, \"tarefas\": [\"Apagar luzes desnecessárias\", \"Reduzir uso de ar condicionado\"], \"recomendacoes\": \"Sugiro cursos de eficiência energética, práticas ESG e sustentabilidade corporativa.\" }")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    @GetMapping("/{idColaborador}")
    public Map<String, Object> recomendar(
        @Parameter(description = "ID do colaborador", example = "1") @PathVariable Long idColaborador) {

        colaboradorRepository.findById(idColaborador)
            .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));

        List<String> tarefasExecutadas = execucaoRepository.findByColaboradorId(idColaborador)
            .stream()
            .map(e -> e.getTarefa().getDescricao())
            .toList();

        String resposta = recomendacaoService.recomendarCursos(tarefasExecutadas);

        return Map.of(
            "idColaborador", idColaborador,
            "tarefas", tarefasExecutadas,
            "recomendacoes", resposta
        );
    }
}