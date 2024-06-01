package com.eduardonakai.pdv_desktop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduardonakai.pdv_desktop.dto.ProdutoDTO;
import com.eduardonakai.pdv_desktop.model.Produto;
import com.eduardonakai.pdv_desktop.service.ProdutoService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto createProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = new Produto(null, produtoDTO.descricao(), produtoDTO.valor(), produtoDTO.categoria(), null);
        return produtoService.save(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        Optional<Produto> produto = produtoService.findById(id);
        if (produto.isPresent()) {
            Produto updatedProduto = produto.get();
            updatedProduto.setDescricao(produtoDTO.descricao());
            updatedProduto.setValor(produtoDTO.valor());
            updatedProduto.setCategoria(produtoDTO.categoria());
            return ResponseEntity.ok(produtoService.save(updatedProduto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.findById(id);
        if (produto.isPresent()) {
            produtoService.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}