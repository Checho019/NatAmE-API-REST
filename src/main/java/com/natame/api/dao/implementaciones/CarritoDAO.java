package com.natame.api.dao.implementaciones;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Carrito;
import com.natame.api.negocio.entidades.Cliente;
import com.natame.api.negocio.entidades.PedidoProducto;
import com.natame.api.utils.Credenciales;
import com.natame.api.utils.OracleConnection;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;

@Service
public class CarritoDAO {


    public Carrito obtenerCarrito(DAODataModel<Long> pedido) throws Exception{
        Credenciales credenciales = pedido.credenciales();
        OracleConnection connCredentials = new OracleConnection(credenciales.user(),credenciales.password());
        Connection conn = connCredentials.getConn();

        String sql = "SELECT * FROM PEDIDO WHERE k_cod_pedido = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, pedido.data());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            return new Carrito(
                    resultSet.getLong("K_COD_PEDIDO"),
                    resultSet.getDate("F_FECHA"),
                    resultSet.getLong("Q_PRECIO_FINAL"),
                    resultSet.getString("K_CORREO"),
                    resultSet.getLong("K_IDENTIFICACION"),
                    resultSet.getString("I_ESTADO").charAt(0),
                    resultSet.getString("N_CIUDAD_ENTREGA"),
                    resultSet.getString("N_DIRECCION_ENTREGA"),
                    Collections.emptyList()
            );
        }
        return null;
    }

    public Carrito agregarCarrito(DAODataModel<Carrito> carrito) throws Exception{
        Credenciales credenciales = carrito.credenciales();
        Carrito car = carrito.data();
        OracleConnection connCredentials = new OracleConnection(credenciales.user(),credenciales.password());
        Connection conn = connCredentials.getConn();

        try {
            conn.setAutoCommit(false);

            // Ingresar representante a la base de datos
            String sql = "INSERT INTO PEDIDO (K_COD_PEDIDO, F_FECHA, Q_PRECIO_FINAL, K_CORREO, K_IDENTIFICACION, I_ESTADO, N_CIUDAD_ENTREGA, N_DIRECCION_ENTREGA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, car.getCodPedido());
            statement.setDate(2, new Date(car.getFecha().getTime()));
            statement.setLong(3, car.getPrecio());
            statement.setString(4, car.getCorreo());
            statement.setLong(5, car.getIdentificacion());
            statement.setString(6, car.getEstado() + "");
            statement.setString(7, car.getCiudadEntrega());
            statement.setString(8,car.getDireccionEntrega());

            System.out.println("Ingresando Carrito");
            statement.executeUpdate();
            System.out.println("AGREGADO Carrito");

            for (PedidoProducto pp : car.getProductos()){
                String pedidoProducto = "INSERT INTO PEDIDO_PRODUCTO (K_COD_PEDIDO,K_CODIGO_PROD,K_CODIGO_REGION,Q_CANTIDAD) VALUES (?,?,?,?)";
                PreparedStatement ppInsert = conn.prepareStatement(pedidoProducto);
                ppInsert.setLong(1,car.getCodPedido());
                ppInsert.setLong(2, pp.getCodProducto());
                ppInsert.setInt(3, pp.getRegion());
                ppInsert.setInt(4, pp.getCantidad());
                ppInsert.executeUpdate();
            }
            System.out.println("Fin de los productos ");

            conn.commit();
        } catch (Exception e){
            conn.rollback();
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return carrito.data();
    }
}
