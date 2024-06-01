package com.eduardonakai.pdv_desktop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardonakai.pdv_desktop.error.ResourceNotFoundException;
import com.eduardonakai.pdv_desktop.model.ItemVenda;
import com.eduardonakai.pdv_desktop.repository.ItemVendaRepository;
import com.eduardonakai.pdv_desktop.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ItemVenda> findAll() {
        return itemVendaRepository.findAll();
    }

    public Optional<ItemVenda> findById(Integer id) {
        return itemVendaRepository.findById(id);
    }

    @Transactional
    public ItemVenda save(ItemVenda itemVenda) {
        // Verifica se o produto existe
        if (produtoRepository.existsById(itemVenda.getProduto().getId())) {
            return itemVendaRepository.save(itemVenda);
        } else {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        itemVendaRepository.deleteById(id);
    }
    public List<ItemVenda> findAllByVendaId(Integer vendaId) {
        return itemVendaRepository.findAllByVendaId(vendaId);
    }
}