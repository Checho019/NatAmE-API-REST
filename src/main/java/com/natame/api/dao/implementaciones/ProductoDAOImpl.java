package com.natame.api.dao.implementaciones;

import com.natame.api.dao.interfaces.ProductoDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Producto;
import com.natame.api.utils.OracleConnection;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductoDAOImpl implements ProductoDAO {

    private final List<Producto> productos;

    public ProductoDAOImpl() throws Exception{
        OracleConnection conn = new OracleConnection("system","Oracle19");
        System.out.println("Conexion creada");
        this.productos = new ArrayList<>(
                Arrays.asList(
                        new Producto(
                                "Aromatel",
                                1,
                                1,
                                1,
                                1000
                        ),
                        new Producto(
                                "Suavitel",
                                2,
                                1,
                                1,
                                1200
                        )
                )
        );
    }

    @Override
    public Producto consultarProducto(DAODataModel<Integer> codigo) throws Exception {
        for (Producto producto: productos){
            if (producto.getCodigo() == codigo.data()){
                return producto;
            }
        }
        return null;
    }

    @Override
    public List<Producto> obtenerProductos(DAODataModel<?> credenciales) throws Exception{
        return this.productos;
    }
}
