package com.eduardonakai.pdv_desktop.dto;
import jakarta.validation.constraints.NotNull;

public record ItemVendaDTO(
        @NotNull Integer quantidade,
        @NotNull Double valorUnitario,
        @NotNull Integer produtoId
) {
}