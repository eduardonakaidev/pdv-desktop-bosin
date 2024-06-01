package com.eduardonakai.pdv_desktop.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduardonakai.pdv_desktop.dto.VendaDTO;
import com.eduardonakai.pdv_desktop.error.ResourceNotFoundException;
import com.eduardonakai.pdv_desktop.model.Cliente;
import com.eduardonakai.pdv_desktop.model.ItemVenda;
import com.eduardonakai.pdv_desktop.model.Produto;
import com.eduardonakai.pdv_desktop.model.Venda;
import com.eduardonakai.pdv_desktop.service.ClienteService;
import com.eduardonakai.pdv_desktop.service.ProdutoService;
import com.eduardonakai.pdv_desktop.service.VendaService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Venda> getAllVendas() {
        return vendaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaById(@PathVariable Integer id) {
        Optional<Venda> venda = vendaService.findById(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venda> createVenda(@Valid @RequestBody VendaDTO vendaDTO) {
        Optional<Cliente> clienteOpt = clienteService.findById(vendaDTO.clienteId());
        if (clienteOpt.isEmpty()) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        Venda venda = new Venda();
        venda.setObservacoes(vendaDTO.observacoes());
        venda.setData(vendaDTO.data());
        venda.setTotal(vendaDTO.total());
        venda.setCliente(clienteOpt.get());

        List<ItemVenda> itensVenda = vendaDTO.itensVenda().stream().map(dto -> {
            Optional<Produto> produtoOpt = produtoService.findById(dto.produtoId());
            if (produtoOpt.isEmpty()) {
                throw new ResourceNotFoundException("Produto não encontrado");
            }

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setQuantidade(dto.quantidade());
            itemVenda.setValorUnitario(dto.valorUnitario());
            itemVenda.setProduto(produtoOpt.get());
            itemVenda.setVenda(venda);
            return itemVenda;
        }).collect(Collectors.toList());

        venda.setItensVenda(itensVenda);
        return ResponseEntity.ok(vendaService.save(venda));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable Integer id, @Valid @RequestBody VendaDTO vendaDTO) {
        Optional<Venda> vendaOpt = vendaService.findById(id);
        if (vendaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Venda venda = vendaOpt.get();
        venda.setObservacoes(vendaDTO.observacoes());
        venda.setData(vendaDTO.data());
        venda.setTotal(vendaDTO.total());

        Optional<Cliente> clienteOpt = clienteService.findById(vendaDTO.clienteId());
        if (clienteOpt.isEmpty()) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        venda.setCliente(clienteOpt.get());

        List<ItemVenda> itensVenda = vendaDTO.itensVenda().stream().map(dto -> {
            Optional<Produto> produtoOpt = produtoService.findById(dto.produtoId());
            if (produtoOpt.isEmpty()) {
                throw new ResourceNotFoundException("Produto não encontrado");
            }

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setQuantidade(dto.quantidade());
            itemVenda.setValorUnitario(dto.valorUnitario());
            itemVenda.setProduto(produtoOpt.get());
            itemVenda.setVenda(venda);
            return itemVenda;
        }).collect(Collectors.toList());

        venda.setItensVenda(itensVenda);
        return ResponseEntity.ok(vendaService.save(venda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable Integer id) {
        Optional<Venda> venda = vendaService.findById(id);
        if (venda.isPresent()) {
            vendaService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}