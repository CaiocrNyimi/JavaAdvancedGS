package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.service.EcoCoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "EcoCoins",
    description = "Gerenciamento de ecoins e conversões",
    extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "7"))
)
@RestController
@RequestMapping("/ecoins")
@RequiredArgsConstructor
public class EcoCoinController {

    private final EcoCoinService service;

    @Operation(summary = "Consultar saldo de ecoins", description = "Retorna o saldo de ecoins de um colaborador")
    @ApiResponse(responseCode = "200", description = "Saldo retornado com sucesso")
    @GetMapping("/{idColaborador}")
    public int saldo(@Parameter(description = "ID do colaborador", example = "1") @PathVariable Long idColaborador) {
        return service.consultarSaldo(idColaborador);
    }

    @Operation(summary = "Converter ecoins", description = "Converte ecoins em recompensas")
    @ApiResponse(responseCode = "204", description = "Conversão realizada com sucesso")
    @PostMapping("/converter")
    public ResponseEntity<Void> converter(
        @Parameter(description = "ID do colaborador", example = "1") @RequestParam Long idColaborador,
        @Parameter(description = "Quantidade de ecoins a converter", example = "50") @RequestParam int quantidade,
        @Parameter(description = "Tipo de recompensa", example = "Vale-presente") @RequestParam String tipoRecompensa
    ) {
        service.converterEcoCoins(idColaborador, quantidade, tipoRecompensa);
        return ResponseEntity.noContent().build();
    }
}