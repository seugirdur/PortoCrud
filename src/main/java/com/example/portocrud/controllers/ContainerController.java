package com.example.portocrud.controllers;

import com.example.portocrud.dtos.ContainerDto;
import com.example.portocrud.enums.CategoriaContainer;
import com.example.portocrud.enums.StatusContainer;
import com.example.portocrud.enums.TipoContainer;
import com.example.portocrud.models.Cliente;
import com.example.portocrud.models.Container;
import com.example.portocrud.services.ClienteService;
import com.example.portocrud.services.ContainerService;
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
@RequestMapping("/containers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContainerController {

    private final ContainerService containerService;
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Object> salvarContainer(@Valid @RequestBody ContainerDto containerDto) {
        if (containerService.existsByNumContainer(containerDto.getNumContainer())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O container com este nome já existe");
        } else {
            Container container = new Container();
            BeanUtils.copyProperties(containerDto, container);
            container.setCategoria(CategoriaContainer.valueOf(containerDto.getCategoria()));
            container.setTipo(TipoContainer.valor(containerDto.getTipo()));
            container.setStatus(StatusContainer.valueOf(containerDto.getStatus()));

            Optional<Cliente> cliente = clienteService.findByUuid(containerDto.getClienteId());

            if(cliente.isPresent()) {
                container.setCliente(cliente.get());
                container.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
                container.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
                return ResponseEntity.status(HttpStatus.CREATED).body(containerService.save(container));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado com uuid: " + containerDto.getClienteId());
            }

        }
    }


    @GetMapping
    public ResponseEntity<Object> listarContainer(@PageableDefault(sort = "createdAt", size = 10, page = 0, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(containerService.listarContainers(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> buscarContainer(@PathVariable(value = "uuid") UUID uuid) {
        Optional<Container> container = containerService.findByUuid(uuid);
        if(container.isPresent()) {
        return ResponseEntity.status(HttpStatus.OK).body(container.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Container nao encontrado com o UUID: " + uuid);
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> atualizarContainer(@PathVariable(value = "uuid") UUID uuid, @Valid @RequestBody ContainerDto containerDto) {
        Optional<Container> container = containerService.findByUuid(uuid);
        if(container.isPresent()) {
            container.get().setNumContainer(containerDto.getNumContainer());
            container.get().setTipo(TipoContainer.valor(containerDto.getTipo()));
            container.get().setCategoria(CategoriaContainer.valueOf(containerDto.getCategoria()));
            container.get().setStatus(StatusContainer.valueOf(containerDto.getStatus()));
            container.get().setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
            return ResponseEntity.status(HttpStatus.OK).body(containerService.save(container.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Container nao encontrado com o UUID: " + uuid);
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> excluirContainer(@PathVariable(value = "uuid") UUID uuid) {
        Optional<Container> container = containerService.findByUuid(uuid);
        if(container.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(containerService.delete(container.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Container nao encontrado com o UUID: " + uuid);
        }
    }



}
