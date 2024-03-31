package com.natame.api.negocio.servicios.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Producto;

import java.util.List;

public interface ProductoServicio {
    Producto consultarProducto(DAODataModel<Integer> codigo) throws Exception;
    List<Producto> obtenerProductos(DAODataModel<?> credenciales) throws Exception;
}
