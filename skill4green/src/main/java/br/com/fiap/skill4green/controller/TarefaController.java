package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.TarefaRequest;
import br.com.fiap.skill4green.dto.response.TarefaResponse;
import br.com.fiap.skill4green.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

  private final TarefaService service;

  @GetMapping
  public Page<TarefaResponse> listar(Pageable pageable) {
    return service.listar(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TarefaResponse> buscar(@PathVariable Long id) {
    return ResponseEntity.ok(service.buscarPorId(id));
  }

  @PostMapping
  public ResponseEntity<TarefaResponse> criar(@RequestBody @Valid TarefaRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TarefaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid TarefaRequest request) {
    return ResponseEntity.ok(service.atualizar(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable Long id) {
    service.remover(id);
    return ResponseEntity.noContent().build();
  }
}