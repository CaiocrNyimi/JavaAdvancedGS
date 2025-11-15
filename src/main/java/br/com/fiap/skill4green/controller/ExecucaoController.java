package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.ExecucaoRequest;
import br.com.fiap.skill4green.dto.response.ExecucaoResponse;
import br.com.fiap.skill4green.service.ExecucaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Execuções",
    description = "CRUD de execuções de tarefas",
    extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "4"))
)
@RestController
@RequestMapping("/execucoes")
@RequiredArgsConstructor
public class ExecucaoController {

    private final ExecucaoService service;

    @Operation(summary = "Listar execuções", description = "Retorna todas as execuções cadastradas com paginação")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public Page<ExecucaoResponse> listar(
        @Parameter(description = "Número da página (0 = primeira)", example = "0")
        @RequestParam(defaultValue = "0") int page,

        @Parameter(description = "Quantidade de registros por página", example = "1000")
        @RequestParam(defaultValue = "1000") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return service.listar(pageRequest);
    }

    @Operation(summary = "Buscar execução por ID", description = "Retorna os dados de uma execução específica")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Execução encontrada"),
        @ApiResponse(responseCode = "404", description = "Execução não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExecucaoResponse> buscar(
        @Parameter(description = "ID da execução", example = "100") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Registrar execução", description = "Registra uma nova execução de tarefa")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Execução registrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ExecucaoResponse> registrar(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados da execução",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                    value = "{ \"idColaborador\": 1, \"idTarefa\": 2, \"data\": \"2025-11-14T22:30:00\", \"resultado\": \"Economia de energia\" }"
                )
            )
        )
        @RequestBody @Valid ExecucaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @Operation(summary = "Atualizar execução", description = "Atualiza os dados de uma execução existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Execução atualizada"),
        @ApiResponse(responseCode = "404", description = "Execução não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExecucaoResponse> atualizar(
        @Parameter(description = "ID da execução", example = "100") @PathVariable Long id,
        @RequestBody @Valid ExecucaoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @Operation(summary = "Remover execução", description = "Remove uma execução pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Execução removida"),
        @ApiResponse(responseCode = "404", description = "Execução não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
        @Parameter(description = "ID da execução", example = "100") @PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}