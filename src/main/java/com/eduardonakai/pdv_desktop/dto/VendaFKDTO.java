package com.eduardonakai.pdv_desktop.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VendaFKDTO(UUID id,String observacoes, LocalDateTime data, int totalincents, UUID clienteId) {
    
}
