package com.natame.api.negocio.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {
    private long codPedido;
    private Date fecha;
    private long precio;
    private String correo;
    private long identificacion;
    private char estado;
    private String ciudadEntrega;
    private String direccionEntrega;

    private List<PedidoProducto> productos;
}
