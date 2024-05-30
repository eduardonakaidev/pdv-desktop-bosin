package com.eduardonakai.pdv_desktop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardonakai.pdv_desktop.model.ClienteModel;
import java.util.Optional;
import java.util.List;



@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID>{
    List<ClienteModel> listAll();

    @SuppressWarnings("null")
    Optional<ClienteModel> findById(UUID id);

   
    @SuppressWarnings({ "null", "unchecked" })
    ClienteModel save(ClienteModel clienteModel);

    @SuppressWarnings("null")
    void deleteById(UUID id);
 
}
