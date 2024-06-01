package com.eduardonakai.pdv_desktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduardonakai.pdv_desktop.dto.ItemVendaDTO;
import com.eduardonakai.pdv_desktop.error.ResourceNotFoundException;
import com.eduardonakai.pdv_desktop.model.ItemVenda;
import com.eduardonakai.pdv_desktop.model.Produto;
import com.eduardonakai.pdv_desktop.service.ItemVendaService;
import com.eduardonakai.pdv_desktop.service.ProdutoService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens-venda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ItemVenda> getAllItensVenda() {
        return itemVendaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemVenda> getItemVendaById(@PathVariable Integer id) {
        Optional<ItemVenda> itemVenda = itemVendaService.findById(id);
        return itemVenda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemVenda> createItemVenda(@Valid @RequestBody ItemVendaDTO itemVendaDTO) {
        Optional<Produto> produtoOpt = produtoService.findById(itemVendaDTO.produtoId());
        if (produtoOpt.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setQuantidade(itemVendaDTO.quantidade());
        itemVenda.setValorUnitario(itemVendaDTO.valorUnitario());
        itemVenda.setProduto(produtoOpt.get());

        return ResponseEntity.ok(itemVendaService.save(itemVenda));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVenda> updateItemVenda(@PathVariable Integer id, @Valid @RequestBody ItemVendaDTO itemVendaDTO) {
        Optional<ItemVenda> itemVendaOpt = itemVendaService.findById(id);
        if (itemVendaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ItemVenda itemVenda = itemVendaOpt.get();
        Optional<Produto> produtoOpt = produtoService.findById(itemVendaDTO.produtoId());
        if (produtoOpt.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        itemVenda.setQuantidade(itemVendaDTO.quantidade());
        itemVenda.setValorUnitario(itemVendaDTO.valorUnitario());
        itemVenda.setProduto(produtoOpt.get());

        return ResponseEntity.ok(itemVendaService.save(itemVenda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemVenda(@PathVariable Integer id) {
        Optional<ItemVenda> itemVenda = itemVendaService.findById(id);
        if (itemVenda.isPresent()) {
            itemVendaService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}