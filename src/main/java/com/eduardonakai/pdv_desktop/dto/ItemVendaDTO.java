package com.eduardonakai.pdv_desktop.dto;
import com.eduardonakai.pdv_desktop.model.ItemVenda;

import jakarta.validation.constraints.NotNull;

public record ItemVendaDTO(
        @NotNull Integer quantidade,
        @NotNull Integer produtoId
) {
   public static ItemVendaDTO fromItemVenda(ItemVenda itemVenda) {
        return new ItemVendaDTO(
            itemVenda.getQuantidade(),
            itemVenda.getProduto().getId()
        );
    }
}