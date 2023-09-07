package com.example.portocrud.models;

import com.example.portocrud.enums.CategoriaContainer;
import com.example.portocrud.enums.StatusContainer;
import com.example.portocrud.enums.TipoContainer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name = "num_container")
    private String numContainer;

    private TipoContainer tipo;

    private StatusContainer status;

    private CategoriaContainer categoria;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference // Evita a serialização do cliente quando listar containers
    private Cliente cliente;


    @ManyToMany(mappedBy = "containers")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<Movimentacao> movimentacoes = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
