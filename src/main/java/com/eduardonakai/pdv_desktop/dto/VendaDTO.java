package com.eduardonakai.pdv_desktop.dto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record VendaDTO(
        String observacoes,
        @NotNull LocalDateTime data,
        @NotNull Double total,
        @NotNull Integer clienteId,
        List<ItemVendaDTO> itensVenda
) {
}