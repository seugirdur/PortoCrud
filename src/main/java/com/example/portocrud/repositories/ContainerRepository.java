package com.example.portocrud.repositories;

import com.example.portocrud.models.Cliente;
import com.example.portocrud.models.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContainerRepository extends JpaRepository<Container, UUID> {
    @Query("SELECT c FROM Container c WHERE c.numContainer = :numContainer")
    Optional<Container> findByName(@Param("numContainer") String numContainer);

    @Query("SELECT c FROM Container c WHERE c.uuid IN :idContainers")
    List<Container> findAllByIdContainers(@Param("idContainers") List<UUID> idContainers);
}
