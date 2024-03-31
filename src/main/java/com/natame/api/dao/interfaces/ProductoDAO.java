package com.natame.api.dao.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Producto;

import java.util.List;

public interface ProductoDAO {
    public Producto consultarProducto(DAODataModel<Integer> codigo) throws Exception;
    public List<Producto> obtenerProductos(DAODataModel<?> credenciales) throws Exception;

}
