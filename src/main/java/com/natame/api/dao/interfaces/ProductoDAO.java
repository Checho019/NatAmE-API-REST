package com.natame.api.dao.interfaces;

import com.natame.api.negocio.entidades.Producto;

import java.util.List;

public interface ProductoDAO {
    public Producto consultarProducto(int codigo) throws Exception;
    public List<Producto> obtenerProductos() throws Exception;

}
