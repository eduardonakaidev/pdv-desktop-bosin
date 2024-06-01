package com.eduardonakai.pdv_desktop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
        @NotBlank String nome,
        String telefone,
        @Email String email
) {
}