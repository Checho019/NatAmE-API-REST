package com.natame.api.dao.implementaciones;

import com.natame.api.dao.interfaces.ProductoDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.dto.ProductoPrecioVista;
import com.natame.api.negocio.entidades.Producto;
import com.natame.api.utils.Credenciales;
import com.natame.api.utils.OracleConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoDAOImpl implements ProductoDAO {


    @Override
    public Producto consultarProducto(DAODataModel<Integer> codigo) throws Exception {

        return null;
    }

    @Override
    public List<ProductoPrecioVista> obtenerProductos(DAODataModel<?> credenciales) throws Exception{
        Credenciales c = credenciales.credenciales();
        OracleConnection connCredentials = new OracleConnection(c.user(),c.password());
        Connection conn = connCredentials.getConn();

        String sql = "SELECT * FROM VISTA_PRODUCTO_PRECIO_STOCK";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<ProductoPrecioVista> productos = new ArrayList<>();
        while (true) {
            if (resultSet.next()) {
                productos.add(
                        new ProductoPrecioVista(
                                resultSet.getString(1),
                                resultSet.getLong(2),
                                resultSet.getLong(3),
                                resultSet.getInt(4)));
            } else {
                break;
            }
        }
        conn.close();
        return productos;
    }
}
