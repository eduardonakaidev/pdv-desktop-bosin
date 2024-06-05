package com.eduardonakai.pdv_desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardonakai.pdv_desktop.error.ResourceNotFoundException;
import com.eduardonakai.pdv_desktop.model.Venda;
import com.eduardonakai.pdv_desktop.repository.ClienteRepository;
import com.eduardonakai.pdv_desktop.repository.VendaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> findById(Integer id) {
        return vendaRepository.findById(id);
    }

    @Transactional
    public Venda save(Venda venda) {
        if (clienteRepository.existsById(venda.getCliente().getId())) {
            return vendaRepository.save(venda);
        } else {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        vendaRepository.deleteById(id);
    }
}