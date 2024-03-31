package com.natame.api.negocio.servicios.implementaciones;

import com.natame.api.dao.interfaces.ProductoDAO;
import com.natame.api.negocio.entidades.Producto;
import com.natame.api.negocio.servicios.interfaces.ProductoServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {
    private final ProductoDAO productoDAO;
    public ProductoServicioImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }
    @Override
    public Producto consultarProducto(int codigo) throws Exception {
        return productoDAO.consultarProducto(codigo);
    }

    @Override
    public List<Producto> obtenerProductos() throws Exception {
        return productoDAO.obtenerProductos();
    }
}
