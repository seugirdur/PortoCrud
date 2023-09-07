package com.example.portocrud.models;

import com.example.portocrud.enums.CategoriaContainer;
import com.example.portocrud.enums.StatusContainer;
import com.example.portocrud.enums.TipoContainer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String name;

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
//    @JsonIgnore // Impede que as movimentações sejam serializadas quando listar clientes
    private List<Container> containers;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
