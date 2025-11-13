package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.ExecucaoRequest;
import br.com.fiap.skill4green.dto.response.ExecucaoResponse;
import br.com.fiap.skill4green.service.ExecucaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/execucoes")
@RequiredArgsConstructor
public class ExecucaoController {

  private final ExecucaoService service;

  @GetMapping
  public Page<ExecucaoResponse> listar(Pageable pageable) {
    return service.listar(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExecucaoResponse> buscar(@PathVariable Long id) {
    return ResponseEntity.ok(service.buscarPorId(id));
  }

  @PostMapping
  public ResponseEntity<ExecucaoResponse> registrar(@RequestBody @Valid ExecucaoRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExecucaoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ExecucaoRequest request) {
    return ResponseEntity.ok(service.atualizar(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable Long id) {
    service.remover(id);
    return ResponseEntity.noContent().build();
  }
}