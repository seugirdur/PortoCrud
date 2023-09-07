package com.example.portocrud.services;

import com.example.portocrud.enums.CategoriaContainer;
import com.example.portocrud.enums.StatusContainer;
import com.example.portocrud.enums.TipoContainer;
import com.example.portocrud.models.Cliente;
import com.example.portocrud.models.Container;
import com.example.portocrud.repositories.ContainerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ContainerServiceTest {

    @InjectMocks
    ContainerService containerService;

    @Mock
    ContainerRepository containerRepository;

    @Test
    void existsByNumContainer() {
        String numContainer = "TEST1234567";

        // Crie um Container simulado que corresponda ao número de contêiner
        Container container = new Container();
        container.setUuid(UUID.randomUUID());
        container.setTipo(TipoContainer.TIPO_20);
        container.setStatus(StatusContainer.CHEIO);
        container.setCategoria(CategoriaContainer.EXPORTACAO);
        container.setNumContainer(numContainer);

        // Simule o comportamento do repository para retornar o Optional com o Container simulado
        when(containerRepository.findByName(numContainer)).thenReturn(Optional.of(container));

        // Chame o método que você está testando
        boolean exists = containerService.existsByNumContainer(numContainer);

        // Verifique se o resultado é verdadeiro, pois o Container foi encontrado
        assertTrue(exists);
    }

    @Test
    void save() {
        Container container = new Container();
        container.setUuid(UUID.randomUUID());
        container.setTipo(TipoContainer.TIPO_20);
        container.setStatus(StatusContainer.CHEIO);
        container.setCategoria(CategoriaContainer.EXPORTACAO);
        container.setNumContainer("TEST1234567");

        Cliente cliente = new Cliente();
        cliente.setUuid(UUID.randomUUID());
        cliente.setName("Empresa B");
        container.setCliente(cliente);

        when(containerService.save(container)).thenReturn(container);
    }

    @Test
    void findByUuid() {
        Container container = new Container();
        UUID uuid = UUID.randomUUID();
        container.setUuid(uuid);
        container.setTipo(TipoContainer.TIPO_20);
        container.setStatus(StatusContainer.CHEIO);
        container.setCategoria(CategoriaContainer.EXPORTACAO);
        container.setNumContainer("TEST2222222");

        when(containerService.findByUuid(uuid)).thenReturn(Optional.of(container));

    }
}