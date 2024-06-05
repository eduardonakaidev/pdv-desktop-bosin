package com.eduardonakai.pdv_desktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduardonakai.pdv_desktop.dto.ClienteDTO;
import com.eduardonakai.pdv_desktop.error.ResourceNotFoundException;
import com.eduardonakai.pdv_desktop.model.Cliente;
import com.eduardonakai.pdv_desktop.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Gerenciamento do cliente",description = "Operações relacionadas ao gerenciamento de clientes")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    @Operation(summary = "listar todos os clientes",description = "operação responsavel pela listagem de clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "listados com sucesso")
    })
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar o cliente pelo id",description = "operação responsavel pela busca de clientes por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "listados com sucesso"),
        @ApiResponse(responseCode = "204",description = "cliente não encontrado")
    })
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @Operation(summary = "Registro de clientes",description = "operação responsavel pela criação e registro dos clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "criado com sucesso"),
    })
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(null, clienteDTO.nome(), clienteDTO.telefone(), clienteDTO.email(), null);
        clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente",description = "operação responsavel pela atualização das informações de um cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "cliente não encontrado"),
        @ApiResponse(responseCode = "200", description = "atualizado com sucesso"),
    })
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        Cliente updatedCliente = cliente.get();
        updatedCliente.setNome(clienteDTO.nome());
        updatedCliente.setTelefone(clienteDTO.telefone());
        updatedCliente.setEmail(clienteDTO.email());
        return ResponseEntity.ok(clienteService.save(updatedCliente));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deletar cliente",description = "operação responsavel pela deletar das informações de um cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "cliente não encontrado"),
        @ApiResponse(responseCode = "204", description = "deletado com sucesso"),
    })
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        clienteService.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }
}