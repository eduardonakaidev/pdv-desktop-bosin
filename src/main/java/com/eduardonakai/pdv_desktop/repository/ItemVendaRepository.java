package com.eduardonakai.pdv_desktop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardonakai.pdv_desktop.model.ClienteModel;
import com.eduardonakai.pdv_desktop.model.ItemVendaModel;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVendaModel, UUID>{
    List<ClienteModel> listAll();

    @SuppressWarnings("null")
    Optional<ItemVendaModel> findById(UUID id);

   
    @SuppressWarnings({ "null", "unchecked" })
    ItemVendaModel save(ItemVendaModel clienteModel);

    @SuppressWarnings("null")
    void deleteById(UUID id);
 
}
