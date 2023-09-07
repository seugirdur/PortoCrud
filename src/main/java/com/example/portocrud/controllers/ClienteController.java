package com.example.portocrud.controllers;

import com.example.portocrud.dtos.ClienteDto;
import com.example.portocrud.models.Cliente;
import com.example.portocrud.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Object> salvarCliente(@Valid @RequestBody ClienteDto clienteDto) {

        if (clienteService.exists(clienteDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O cliente com este nome já existe");
        } else {

            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteDto, cliente);
            cliente.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
            cliente.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
        }
    }

    @GetMapping
    public ResponseEntity<Object> listarClientes(@PageableDefault(sort = "name", size = 10, page = 0, direction = Sort.Direction.ASC)Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarClientes(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> buscaCliente(@PathVariable(value = "uuid") UUID uuid) {
        Optional<Cliente> cliente = clienteService.existsByUuid(uuid);
        if (cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado com o uuid: " + uuid);
        }
    }
}
