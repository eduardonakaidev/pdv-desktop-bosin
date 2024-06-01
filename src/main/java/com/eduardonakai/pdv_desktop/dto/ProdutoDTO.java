package com.eduardonakai.pdv_desktop.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
        @NotBlank String descricao,
        @NotNull Double valor,
        String categoria
) {
}