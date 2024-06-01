package com.eduardonakai.pdv_desktop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardonakai.pdv_desktop.model.Cliente;





@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
 
}
