package com.eduardonakai.pdv_desktop.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.eduardonakai.pdv_desktop.dto.ItemVendaDTO;
import com.eduardonakai.pdv_desktop.dto.VendaDTO;
import com.eduardonakai.pdv_desktop.model.Venda;

@Component
public class VendaConverter {
    
    public static VendaDTO toDTO(Venda venda) {
        List<ItemVendaDTO> itemVendaDTOList = venda.getItensVenda().stream().map(item ->
            new ItemVendaDTO(
                item.getQuantidade(),
                item.getProduto().getId()  // Only include the product ID
            )
        ).collect(Collectors.toList());

        return new VendaDTO(
            venda.getObservacoes(),
            venda.getData(),
            venda.getTotal(),
            venda.getCliente().getId(),
            itemVendaDTOList
        );
    }
}