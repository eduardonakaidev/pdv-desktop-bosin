package com.eduardonakai.pdv_desktop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardonakai.pdv_desktop.model.Venda;

public interface VendaRepository  extends JpaRepository<Venda, Integer>{
    @SuppressWarnings("null")
    @EntityGraph(attributePaths = "itensVenda")
    List<Venda> findAll();
}
