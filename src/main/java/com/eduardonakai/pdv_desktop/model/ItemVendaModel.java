package com.eduardonakai.pdv_desktop.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class ItemVendaModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private int quantidade;

  private int valorunitario;

  private int valortotal;

  private VendaModel venda;

  private ProdutoModel produto;
}
