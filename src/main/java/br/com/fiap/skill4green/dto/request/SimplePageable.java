package br.com.fiap.skill4green.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SimplePageable {

    @Schema(description = "Número da página (0 = primeira)", example = "0", defaultValue = "0")
    private int page = 0;

    @Schema(description = "Quantidade de registros por página (use um valor alto para trazer todos)", example = "1000", defaultValue = "1000")
    private int size = 1000;

    @Schema(description = "Critério de ordenação (null = sem ordenação)", nullable = true)
    private String sort;
}