package com.natame.api.negocio.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProducto {
    private long codProducto;
    private int region;
    private int cantidad;
}
