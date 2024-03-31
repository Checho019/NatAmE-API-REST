package com.natame.api.negocio.entidades;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @NotNull
    private String nombre;
    @NotNull
    private int codigo;
    @NotNull
    private int subCategoria;
    @NotNull
    private int categoria;
    @NotNull
    private long precioUnitario;
}
