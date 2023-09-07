package com.example.portocrud.dtos;

import com.example.portocrud.valids.CategoriaContainerValid;
import com.example.portocrud.valids.StatusContainerValid;
import com.example.portocrud.valids.TipoContainerValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
public class ContainerDto {

    @NotBlank(message = "O campo de id do cliente não deve estar vazio")
    @Pattern(regexp = "^[A-Za-z]{4}\\d{7}$", message = "Deve conter 4 letras seguidas por 7 números.")
    private String numContainer;

    @NotNull
    @TipoContainerValid
    private int tipo;

    @NotBlank
    @StatusContainerValid
    private String status;

    @NotBlank
    @CategoriaContainerValid
    private String categoria;

    @NotNull(message = "O campo de id do cliente nao deve estar vazio")
    private UUID clienteId;

}
