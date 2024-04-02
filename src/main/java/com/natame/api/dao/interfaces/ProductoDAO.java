package com.natame.api.dao.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.dto.ProductoPrecioVista;
import com.natame.api.negocio.entidades.Producto;

import java.util.List;

public interface ProductoDAO {
    Producto consultarProducto(DAODataModel<Integer> codigo) throws Exception;
    List<ProductoPrecioVista> obtenerProductos(DAODataModel<?> credenciales) throws Exception;
}
