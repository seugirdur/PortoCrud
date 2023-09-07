package com.example.portocrud.services;

import com.example.portocrud.models.Cliente;
import com.example.portocrud.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public boolean exists(String name) {
        if(clienteRepository.findByName(name).isPresent()) {
            return true;
        }
        return false;
    }

    public Object listarClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Optional<Cliente> existsByUuid(UUID uuid) {
        return clienteRepository.findById(uuid);
    }

    public Optional<Cliente> findByUuid(UUID clienteId) {
        return clienteRepository.findById(clienteId);
    }
}
