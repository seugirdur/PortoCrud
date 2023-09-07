package com.example.portocrud.services;

import com.example.portocrud.models.Container;
import com.example.portocrud.repositories.ContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class ContainerService {

    private final ContainerRepository containerRepository;

    public boolean existsByNumContainer(String numContainer) {
        return containerRepository.findByName(numContainer).isPresent();
    }

    public Container save(Container container) {
        return containerRepository.save(container);
    }

    public Object listarContainers(Pageable pageable) {
        return containerRepository.findAll(pageable);
    }

    public Optional<Container> findByUuid(UUID uuid) {
        return containerRepository.findById(uuid);
    }

    public Object delete(Container container) {
        try {
            containerRepository.delete(container);
            return "Deletado com sucesso!";
        } catch (Exception e) {
            return "Erro ao deletar";
        }
    }

    public List<Container> findAllById(List<UUID> idContainers) {
        return containerRepository.findAllByIdContainers(idContainers);
    }
}
