package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.MissaoRequest;
import br.com.fiap.skill4green.dto.response.MissaoResponse;
import br.com.fiap.skill4green.service.MissaoService;
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
    name = "Missões",
    description = "CRUD de missões sustentáveis",
    extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "5"))
)
@RestController
@RequestMapping("/missoes")
@RequiredArgsConstructor
public class MissaoController {

    private final MissaoService service;

    @Operation(summary = "Listar missões", description = "Retorna todas as missões cadastradas com paginação")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public Page<MissaoResponse> listar(
        @Parameter(description = "Número da página (0 = primeira)", example = "0")
        @RequestParam(defaultValue = "0") int page,

        @Parameter(description = "Quantidade de registros por página", example = "1000")
        @RequestParam(defaultValue = "1000") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return service.listar(pageRequest);
    }

    @Operation(summary = "Buscar missão por ID", description = "Retorna os dados de uma missão específica")
    @ApiResponse(responseCode = "200", description = "Missão encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<MissaoResponse> buscar(
        @Parameter(description = "ID da missão", example = "5") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar missão", description = "Cadastra uma nova missão")
    @ApiResponse(responseCode = "201", description = "Missão criada")
    @PostMapping
    public ResponseEntity<MissaoResponse> criar(@RequestBody @Valid MissaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @Operation(summary = "Atualizar missão", description = "Atualiza os dados de uma missão existente")
    @ApiResponse(responseCode = "200", description = "Missão atualizada")
    @PutMapping("/{id}")
    public ResponseEntity<MissaoResponse> atualizar(
        @Parameter(description = "ID da missão", example = "5") @PathVariable Long id,
        @RequestBody @Valid MissaoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @Operation(summary = "Remover missão", description = "Remove uma missão pelo ID")
    @ApiResponse(responseCode = "204", description = "Missão removida")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
        @Parameter(description = "ID da missão", example = "5") @PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}