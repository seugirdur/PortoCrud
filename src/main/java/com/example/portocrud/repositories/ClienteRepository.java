package com.example.portocrud.repositories;

import com.example.portocrud.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    @Query("SELECT c FROM Cliente c WHERE c.name = :name")
    Optional<Cliente> findByName(@Param("name") String name);
}
