package com.eduardonakai.pdv_desktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduardonakai.pdv_desktop.dto.ClienteDTO;
import com.eduardonakai.pdv_desktop.error.ResourceNotFoundException;
import com.eduardonakai.pdv_desktop.model.Cliente;
import com.eduardonakai.pdv_desktop.service.ClienteService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(null, clienteDTO.nome(), clienteDTO.telefone(), clienteDTO.email(), null);
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        clienteService.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }
}