package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.service.EcoCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecoins")
@RequiredArgsConstructor
public class EcoCoinController {

  private final EcoCoinService service;

  @GetMapping("/{idColaborador}")
  public int saldo(@PathVariable Long idColaborador) {
    return service.consultarSaldo(idColaborador);
  }

  @PostMapping("/converter")
  public ResponseEntity<Void> converter(
    @RequestParam Long idColaborador,
    @RequestParam int quantidade,
    @RequestParam String tipoRecompensa
  ) {
    service.converterEcoCoins(idColaborador, quantidade, tipoRecompensa);
    return ResponseEntity.ok().build();
  }
}