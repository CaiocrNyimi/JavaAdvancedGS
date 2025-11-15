package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.TarefaRequest;
import br.com.fiap.skill4green.dto.response.TarefaResponse;
import br.com.fiap.skill4green.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    name = "Tarefas",
    description = "CRUD de tarefas sustentáveis",
    extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "6"))
)
@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService service;

    @Operation(summary = "Listar tarefas", description = "Retorna todas as tarefas cadastradas com paginação")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public Page<TarefaResponse> listar(
        @Parameter(description = "Número da página (0 = primeira)", example = "0")
        @RequestParam(defaultValue = "0") int page,

        @Parameter(description = "Quantidade de registros por página", example = "1000")
        @RequestParam(defaultValue = "1000") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return service.listar(pageRequest);
    }

    @Operation(summary = "Buscar tarefa por ID", description = "Retorna os dados de uma tarefa específica")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscar(
        @Parameter(description = "ID da tarefa", example = "20") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar tarefa", description = "Cadastra uma nova tarefa")
    @ApiResponse(responseCode = "201", description = "Tarefa criada")
    @PostMapping
    public ResponseEntity<TarefaResponse> criar(@RequestBody @Valid TarefaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados de uma tarefa existente")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada")
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizar(
        @Parameter(description = "ID da tarefa", example = "20") @PathVariable Long id,
        @RequestBody @Valid TarefaRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @Operation(summary = "Remover tarefa", description = "Remove uma tarefa pelo ID")
    @ApiResponse(responseCode = "204", description = "Tarefa removida")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
        @Parameter(description = "ID da tarefa", example = "20") @PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}