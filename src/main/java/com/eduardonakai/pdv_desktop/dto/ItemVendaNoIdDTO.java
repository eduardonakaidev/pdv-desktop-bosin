package com.eduardonakai.pdv_desktop.dto;

import java.util.UUID;

public record ItemVendaNoIdDTO (int quantidade, int valorunitarioincents,int valortotalincents, UUID vendaId, UUID produtoId){
    
}
