package com.eduardonakai.pdv_desktop.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardonakai.pdv_desktop.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
