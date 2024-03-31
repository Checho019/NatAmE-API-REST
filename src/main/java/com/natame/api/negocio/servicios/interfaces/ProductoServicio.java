package com.natame.api.negocio.servicios.interfaces;

import com.natame.api.negocio.entidades.Producto;

import java.util.List;

public interface ProductoServicio {
    public Producto consultarProducto(int codigo) throws Exception;
    public List<Producto> obtenerProductos() throws Exception;
}
