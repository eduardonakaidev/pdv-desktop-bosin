package com.eduardonakai.pdv_desktop.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardonakai.pdv_desktop.model.Produto;
import com.eduardonakai.pdv_desktop.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Integer id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deleteById(Integer id) {
        produtoRepository.deleteById(id);
    }
}