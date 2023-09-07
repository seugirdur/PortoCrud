package com.example.portocrud.controllers;

import com.example.portocrud.dtos.MovimentacaoDto;
import com.example.portocrud.enums.TipoMovimentacao;
import com.example.portocrud.models.Container;
import com.example.portocrud.models.Movimentacao;
import com.example.portocrud.services.ContainerService;
import com.example.portocrud.services.MovimentacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/movimentacoes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<Object> salvarMovimentacao(@Valid @RequestBody MovimentacaoDto movimentacaoDto) {
        Movimentacao movimentacao = new Movimentacao();
        BeanUtils.copyProperties(movimentacaoDto, movimentacao);
        movimentacao.setTipo(TipoMovimentacao.valueOf(movimentacaoDto.getTipo()));

        List<Container> containers = movimentacaoService.findAllById(movimentacaoDto.getIdcontainers());

        Set<Container> containerSet = new HashSet<>();

        containerSet.addAll(containers);

        movimentacao.setContainers(containerSet);

        movimentacao.setStartedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoService.save(movimentacao));
    }

    @GetMapping
    public ResponseEntity<Object> listarMovimentacoes(@PageableDefault(size = 10, sort = "startedAt", page = 0, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.listarMovimentacoes(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> buscarMovimentacao(@PathVariable(value = "uuid") UUID uuid) {
        Optional<Movimentacao> movimentacao = movimentacaoService.findById(uuid);
        if (movimentacao.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body((movimentacao.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado movimentação com uuid: " + uuid);
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> salvarFinalizacao(@PathVariable(value = "uuid") UUID uuid) {
        Optional<Movimentacao> movimentacao = movimentacaoService.findById(uuid);
        if (movimentacao.isPresent()) {
            movimentacao.get().setFinishedAt(LocalDateTime.now(ZoneId.of("UTC")));
            return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.save(movimentacao.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado movimentação com uuid: " + uuid);
        }
    }
}
