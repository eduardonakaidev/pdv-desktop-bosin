package com.eduardonakai.pdv_desktop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
@Entity(name = "venda")
@Table(name = "venda")
public class VendaModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String observacoes;

  private LocalDateTime data;

  private int totalincents;

  private ClienteModel cliente;

  private ArrayList<ItemVendaModel> itensvenda;
}
