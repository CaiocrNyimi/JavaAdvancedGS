package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.dto.request.ExecucaoRequest;
import br.com.fiap.skill4green.dto.response.ExecucaoResponse;
import br.com.fiap.skill4green.service.ExecucaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/execucoes")
@RequiredArgsConstructor
public class ExecucaoController {

  private final ExecucaoService service;

  @GetMapping
  public List<ExecucaoResponse> listar() {
    return service.listar();
  }

  @PostMapping
  public ResponseEntity<ExecucaoResponse> registrar(@RequestBody @Valid ExecucaoRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
  }
}