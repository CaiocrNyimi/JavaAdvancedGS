package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.response.RankingResponse;
import br.com.fiap.skill4green.service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Ranking",
    description = "Consulta de ranking por setor",
    extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "8"))
)
@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService service;

    @Operation(summary = "Consultar ranking", description = "Retorna o ranking de setores por ecoins acumulados")
    @ApiResponse(responseCode = "200", description = "Ranking retornado com sucesso")
    @GetMapping
    public List<RankingResponse> consultar() {
        return service.consultarRanking();
    }
}