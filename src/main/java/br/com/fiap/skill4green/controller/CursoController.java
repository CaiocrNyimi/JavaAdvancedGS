package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.CursoRequest;
import br.com.fiap.skill4green.dto.response.CursoResponse;
import br.com.fiap.skill4green.service.CursoService;
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
    name = "Cursos",
    description = "CRUD de cursos sustentáveis",
    extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "3"))
)
@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService service;

    @Operation(summary = "Listar cursos", description = "Retorna todos os cursos cadastrados com paginação")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public Page<CursoResponse> listar(
        @Parameter(description = "Número da página (0 = primeira)", example = "0")
        @RequestParam(defaultValue = "0") int page,

        @Parameter(description = "Quantidade de registros por página", example = "1000")
        @RequestParam(defaultValue = "1000") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return service.listar(pageRequest);
    }

    @Operation(summary = "Buscar curso por ID", description = "Retorna os dados de um curso específico")
    @ApiResponse(responseCode = "200", description = "Curso encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscar(
        @Parameter(description = "ID do curso", example = "10") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar curso", description = "Cadastra um novo curso")
    @ApiResponse(responseCode = "201", description = "Curso criado")
    @PostMapping
    public ResponseEntity<CursoResponse> criar(@RequestBody @Valid CursoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @Operation(summary = "Atualizar curso", description = "Atualiza os dados de um curso existente")
    @ApiResponse(responseCode = "200", description = "Curso atualizado")
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> atualizar(
        @Parameter(description = "ID do curso", example = "10") @PathVariable Long id,
        @RequestBody @Valid CursoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @Operation(summary = "Remover curso", description = "Remove um curso pelo ID")
    @ApiResponse(responseCode = "204", description = "Curso removido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
        @Parameter(description = "ID do curso", example = "10") @PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}