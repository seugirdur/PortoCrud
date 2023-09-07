package com.example.portocrud.services;

import com.example.portocrud.models.Container;
import com.example.portocrud.models.Movimentacao;
import com.example.portocrud.repositories.ContainerRepository;
import com.example.portocrud.repositories.MovimentacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ContainerRepository containerRepository;

    public Movimentacao save(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    public Object listarMovimentacoes(Pageable pageable) {
        return movimentacaoRepository.findAll(pageable);
    }

    public List<Container> findAllById(List<UUID> idcontainers) {
        return containerRepository.findAllByIdContainers(idcontainers);
    }

    public Optional<Movimentacao> findById(UUID uuid) {
        return movimentacaoRepository.findById(uuid);
    }
}
