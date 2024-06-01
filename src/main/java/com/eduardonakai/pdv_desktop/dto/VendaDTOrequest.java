package com.eduardonakai.pdv_desktop.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record VendaDTOrequest(
        String observacoes,
        @NotNull LocalDateTime data,
        @NotNull Integer clienteId,
        List<ItemVendaDTO> itensVenda
) {
}