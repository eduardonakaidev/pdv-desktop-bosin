package com.eduardonakai.pdv_desktop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eduardonakai.pdv_desktop.model.ItemVendaModel;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVendaModel, UUID>{
    
}