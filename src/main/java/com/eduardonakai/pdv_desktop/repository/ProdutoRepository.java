package com.eduardonakai.pdv_desktop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardonakai.pdv_desktop.model.ClienteModel;
import com.eduardonakai.pdv_desktop.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {
    List<ClienteModel> listAll();

    @SuppressWarnings("null")
    Optional<ProdutoModel> findById(UUID id);

    @SuppressWarnings({ "null", "unchecked" })
    ProdutoModel save(ProdutoModel clienteModel);

    @SuppressWarnings("null")
    void deleteById(UUID id);
}
