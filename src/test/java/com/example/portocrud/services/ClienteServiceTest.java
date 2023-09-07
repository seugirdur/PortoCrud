package com.example.portocrud.services;

import com.example.portocrud.models.Cliente;
import com.example.portocrud.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;


    @Test
    public void salvarClienteTest() {
        Cliente cliente = new Cliente();
        cliente.setUuid(UUID.randomUUID());
        cliente.setName("Empresa A");

        when(clienteService.save(cliente)).thenReturn(cliente);

    }

}