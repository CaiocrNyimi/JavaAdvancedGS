package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.ColaboradorRequest;
import br.com.fiap.skill4green.dto.response.ColaboradorResponse;
import br.com.fiap.skill4green.service.ColaboradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
@RequiredArgsConstructor
public class ColaboradorController {

  private final ColaboradorService service;

  @GetMapping
  public List<ColaboradorResponse> listar() {
    return service.listar();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ColaboradorResponse> buscar(@PathVariable Long id) {
    return ResponseEntity.ok(service.buscarPorId(id));
  }

  @PostMapping
  public ResponseEntity<ColaboradorResponse> criar(@RequestBody @Valid ColaboradorRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ColaboradorResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ColaboradorRequest request) {
    return ResponseEntity.ok(service.atualizar(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable Long id) {
    service.remover(id);
    return ResponseEntity.noContent().build();
  }
}