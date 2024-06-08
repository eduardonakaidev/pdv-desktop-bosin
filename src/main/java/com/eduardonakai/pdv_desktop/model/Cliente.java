package com.eduardonakai.pdv_desktop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.eduardonakai.pdv_desktop.error.Numeric;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cliente")
@Table(name = "cliente")
public class Cliente {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;
    @NotBlank
    @Numeric(message = "O telefone so pode ter numeros exemplo: 00000000000")
    private String telefone;
    @NotBlank
    @Email
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;
}
