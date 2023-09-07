package com.example.portocrud.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteDto {

    @NotBlank(message = "Escreva um nome para adicionar um cliente")
    private String name;

}
