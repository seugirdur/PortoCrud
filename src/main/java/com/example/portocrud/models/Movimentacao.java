package com.example.portocrud.models;

import com.example.portocrud.enums.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name = "tipo_movimentacao")
    private TipoMovimentacao tipo;

    @ManyToMany
    @JoinTable(name = "movimentacao_container",
            joinColumns = @JoinColumn(name = "movimentacao_id"),
            inverseJoinColumns = @JoinColumn(name = "container_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore// Evita a serialização do cliente quando listar movimentações
    private Set<Container> containers = new HashSet<>();

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Nullable
    @Column(name = "finished_at")
    private LocalDateTime finishedAt;


}
