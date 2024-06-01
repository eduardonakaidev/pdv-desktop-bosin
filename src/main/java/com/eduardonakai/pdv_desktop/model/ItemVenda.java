package com.eduardonakai.pdv_desktop.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "item_venda")
@Table(name = "item_venda")
public class ItemVenda {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Double valorUnitario;

    @NotNull
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "fk_Venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "fk_Produto_id")
    private Produto produto;
}
