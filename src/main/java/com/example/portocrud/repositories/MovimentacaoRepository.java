package com.example.portocrud.repositories;

import com.example.portocrud.models.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, UUID> {
}
