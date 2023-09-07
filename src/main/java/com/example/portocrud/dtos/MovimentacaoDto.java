package com.example.portocrud.dtos;

import com.example.portocrud.valids.TipoMovimentacaoValid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
public class MovimentacaoDto {

    @TipoMovimentacaoValid
    @NotBlank
    private String tipo;

    private List<UUID> idcontainers;
}
