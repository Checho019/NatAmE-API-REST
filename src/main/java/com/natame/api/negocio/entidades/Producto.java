package com.natame.api.negocio.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private String nombre;
    private int codigo;
    private int subCategoria;
    private int categoria;
    private long precioUnitario;
}
