package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.MissaoRequest;
import br.com.fiap.skill4green.dto.response.MissaoResponse;
import br.com.fiap.skill4green.service.MissaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
@RequiredArgsConstructor
public class MissaoController {

  private final MissaoService service;

  @GetMapping
  public Page<MissaoResponse> listar(Pageable pageable) {
    return service.listar(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MissaoResponse> buscar(@PathVariable Long id) {
    return ResponseEntity.ok(service.buscarPorId(id));
  }

  @PostMapping
  public ResponseEntity<MissaoResponse> criar(@RequestBody @Valid MissaoRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<MissaoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid MissaoRequest request) {
    return ResponseEntity.ok(service.atualizar(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable Long id) {
    service.remover(id);
    return ResponseEntity.noContent().build();
  }
}