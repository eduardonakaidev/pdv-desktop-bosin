package com.eduardonakai.pdv_desktop.model;

import org.hibernate.validator.constraints.Length;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produto")
@Table(name = "produto")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 200)
    private String descricao;

    @NotNull
    private int valorincents;

    @NotBlank
    @NotNull
    @NotEmpty
    private String categoria;
}
