package com.eduardonakai.pdv_desktop.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "venda")
@Table(name = "venda")
public class Venda {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String observacoes;

    @NotNull
    private LocalDateTime data;

    @NotNull
    private Double total;

    @ManyToOne
    @JoinColumn(name = "fk_Cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itensVenda;
}
