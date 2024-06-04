package com.eduardonakai.pdv_desktop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardonakai.pdv_desktop.model.Venda;

@Repository
public interface VendaRepository  extends JpaRepository<Venda, Integer>{
    @EntityGraph(attributePaths = "itensVenda")
    List<Venda> findAll();
}
