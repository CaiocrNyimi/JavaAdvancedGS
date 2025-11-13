package br.com.fiap.skill4green.controller;

import br.com.fiap.skill4green.ai.IAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ia")
@RequiredArgsConstructor
public class IAController {

    private final IAService iaService;

    @GetMapping("/trilha")
    public ResponseEntity<String> recomendarTrilha(
            @RequestParam String nome,
            @RequestParam String setor,
            @RequestParam String nivelVerde
    ) {
        String resposta = iaService.recomendarTrilha(nome, setor, nivelVerde);
        return ResponseEntity.ok(resposta);
    }
}