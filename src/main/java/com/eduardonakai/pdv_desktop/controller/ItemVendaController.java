package com.eduardonakai.pdv_desktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduardonakai.pdv_desktop.dto.ItemVendaDTO;
import com.eduardonakai.pdv_desktop.error.ResourceNotFoundException;
import com.eduardonakai.pdv_desktop.model.ItemVenda;
import com.eduardonakai.pdv_desktop.model.Produto;
import com.eduardonakai.pdv_desktop.service.ItemVendaService;
import com.eduardonakai.pdv_desktop.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens-venda")
@Tag(name = "Gerenciamento de itemvenda",description = "responsavel pela gestão dos itemvenda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/")
    @Operation(summary = "listar todos os item venda",description = "operação responsavel pela listagem de item venda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "listados com sucesso")
    })
    public ResponseEntity<List<ItemVenda>> getAllItensVenda() {
        List<ItemVenda> ilist =  itemVendaService.findAll();
        return ResponseEntity.ok().body(ilist);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar dos itemvenda pelo id",description = "Responsavel pela busca dos itemvenda pelo id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "listados com sucesso"),
        @ApiResponse(responseCode = "204",description = "itemvenda não encontrado")
    })
    public ResponseEntity<ItemVenda> getItemVendaById(@PathVariable Integer id) {
        Optional<ItemVenda> itemVenda = itemVendaService.findById(id);
        return itemVenda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @Operation(summary = "Registro de itemvenda",description = "operação responsavel pela criação e registro dos itemvenda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "criado com sucesso"),
        @ApiResponse(responseCode = "404", description = "produto não encontrado"),
    })
    public ResponseEntity<ItemVenda> createItemVenda(@Valid @RequestBody ItemVendaDTO itemVendaDTO) {
        Optional<Produto> produtoOpt = produtoService.findById(itemVendaDTO.produtoId());
        if (produtoOpt.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setQuantidade(itemVendaDTO.quantidade());
        itemVenda.setValorUnitario(produtoOpt.get().getValor());
        itemVenda.setProduto(produtoOpt.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(itemVendaService.save(itemVenda));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar itemvenda",description = "operação responsavel pela atualização das informações de um itemvenda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "itemvenda não encontrado"),
        @ApiResponse(responseCode = "404", description = "produto não encontrado"),
        @ApiResponse(responseCode = "200", description = "atualizado com sucesso"),
    })
    public ResponseEntity<ItemVenda> updateItemVenda(@PathVariable Integer id, @Valid @RequestBody ItemVendaDTO itemVendaDTO) {
        Optional<ItemVenda> itemVendaOpt = itemVendaService.findById(id);
        if (itemVendaOpt.isEmpty()) {
            throw new ResourceNotFoundException("Item de venda não encontrado");
        }

        ItemVenda itemVenda = itemVendaOpt.get();
        Optional<Produto> produtoOpt = produtoService.findById(itemVendaDTO.produtoId());
        if (produtoOpt.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        itemVenda.setQuantidade(itemVendaDTO.quantidade());
        itemVenda.setValorUnitario(produtoOpt.get().getValor());
        itemVenda.setProduto(produtoOpt.get());

        return ResponseEntity.ok(itemVendaService.save(itemVenda));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deletar itemvenda",description = "operação responsavel pela deletar das informações de um itemvenda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "itemvenda não encontrado"),
        @ApiResponse(responseCode = "204", description = "deletado com sucesso"),
    })
    public ResponseEntity<Void> deleteItemVenda(@PathVariable Integer id) {
        Optional<ItemVenda> itemVenda = itemVendaService.findById(id);
        if (itemVenda.isEmpty()) {
            throw new ResourceNotFoundException("Item de venda não encontrado");
        }

        itemVendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/venda/{id}")
    @Operation(summary = "listar todos os item venda pelo id da venda",description = "operação responsavel pela listagem de item venda pelo id da venda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "listados com sucesso")
    })
    public ResponseEntity<List<ItemVenda>> getAllItensVendaByVendaId(@PathVariable Integer id) {
        List<ItemVenda> itensVenda = itemVendaService.findAllByVendaId(id);
        return ResponseEntity.ok(itensVenda);
    }
}