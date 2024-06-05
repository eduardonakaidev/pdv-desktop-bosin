package com.eduardonakai.pdv_desktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduardonakai.pdv_desktop.dto.ProdutoDTO;
import com.eduardonakai.pdv_desktop.error.ResourceNotFoundException;
import com.eduardonakai.pdv_desktop.model.Produto;
import com.eduardonakai.pdv_desktop.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Gerenciamento de produtos",description = "responsavel pela gestão dos produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @GetMapping("/")
    @Operation(summary = "listar todos os produtos",description = "operação responsavel pela listagem de produtos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "listados com sucesso")
    })
    public List<Produto> getAllProdutos() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar do produto pelo id",description = "Responsavel pela busca do pruduto pelo id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "listados com sucesso"),
        @ApiResponse(responseCode = "204",description = "produto não encontrado")
    })
    public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @Operation(summary = "Registro de produtos",description = "operação responsavel pela criação e registro do produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "criado com sucesso")
    })
    public Produto createProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = new Produto(null, produtoDTO.descricao(), produtoDTO.valor(), produtoDTO.categoria(), null);
        return produtoService.save(produto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar o produto",description = "operação responsavel pela atualização das informações de um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "produto não encontrado"),
        @ApiResponse(responseCode = "200", description = "atualizado com sucesso"),
    })
    public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        Optional<Produto> produto = produtoService.findById(id);
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        Produto updatedProduto = produto.get();
        updatedProduto.setDescricao(produtoDTO.descricao());
        updatedProduto.setValor(produtoDTO.valor());
        updatedProduto.setCategoria(produtoDTO.categoria());
        return ResponseEntity.ok(produtoService.save(updatedProduto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deletar o produto",description = "operação responsavel pela deletar das informações de um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "produto não encontrado"),
        @ApiResponse(responseCode = "204", description = "deletado com sucesso"),
    })
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.findById(id);
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        produtoService.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}