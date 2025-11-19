package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.ColaboradorRequest;
import br.com.fiap.skill4green.dto.response.ColaboradorResponse;
import br.com.fiap.skill4green.service.ColaboradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    name = "Colaboradores",
    description = "CRUD de colaboradores",
    extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "2"))
)
@RestController
@RequestMapping("/colaboradores")
@RequiredArgsConstructor
public class ColaboradorController {

    private final ColaboradorService service;

    @Operation(summary = "Listar colaboradores", description = "Retorna todos os colaboradores cadastrados com paginação")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public Page<ColaboradorResponse> listar(
        @Parameter(description = "Número da página (0 = primeira)", example = "0")
        @RequestParam(defaultValue = "0") int page,

        @Parameter(description = "Quantidade de registros por página", example = "1000")
        @RequestParam(defaultValue = "1000") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return service.listar(pageRequest);
    }

    @Operation(summary = "Buscar colaborador por ID", description = "Retorna os dados de um colaborador específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Colaborador encontrado"),
        @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> buscar(
        @Parameter(description = "ID do colaborador", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar colaborador", description = "Cadastra um novo colaborador")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Colaborador criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ColaboradorResponse> criar(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados do colaborador",
            required = true,
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{ \"nome\": \"Admin Silva\", \"setor\": \"TI\", \"email\": \"admin@empresa.com\", \"senha\": \"admin123\", \"perfil\": \"ADMIN\", \"nivelVerde\": \"MENTOR\", \"ecoins\": 10000 }")
            )
        )
        @RequestBody @Valid ColaboradorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @Operation(summary = "Atualizar colaborador", description = "Atualiza os dados de um colaborador existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Colaborador atualizado"),
        @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> atualizar(
        @Parameter(description = "ID do colaborador", example = "1") @PathVariable Long id,
        @RequestBody @Valid ColaboradorRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @Operation(summary = "Remover colaborador", description = "Remove um colaborador pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Colaborador removido"),
        @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
        @Parameter(description = "ID do colaborador", example = "1") @PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}