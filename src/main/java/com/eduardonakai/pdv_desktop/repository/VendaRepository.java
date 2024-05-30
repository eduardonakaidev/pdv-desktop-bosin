package com.eduardonakai.pdv_desktop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardonakai.pdv_desktop.model.ClienteModel;
import com.eduardonakai.pdv_desktop.model.VendaModel;

public interface VendaRepository  extends JpaRepository<VendaModel, UUID>{
    List<ClienteModel> listAll();

    @SuppressWarnings("null")
    Optional<VendaModel> findById(UUID id);

    @SuppressWarnings({ "null", "unchecked" })
    VendaModel save(VendaModel clienteModel);

    @SuppressWarnings("null")
    void deleteById(UUID id);
}
