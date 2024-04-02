package com.natame.api.dao.implementaciones;

import com.natame.api.dao.interfaces.ClienteDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Cliente;
import com.natame.api.negocio.entidades.Representante;
import com.natame.api.utils.Credenciales;
import com.natame.api.utils.OracleConnection;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public Cliente obtenerCliente(DAODataModel<Long> id) throws Exception{
        Credenciales credenciales = id.credenciales();
        OracleConnection connCredentials = new OracleConnection(credenciales.user(),credenciales.password());
        Connection conn = connCredentials.getConn();

        String sql = "SELECT * FROM clients WHERE k_identificacion = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id.data());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            return new Cliente(
                    resultSet.getLong("k_identificacion"),
                    resultSet.getString("k_correo"),
                    resultSet.getString("n_nombre1"),
                    resultSet.getString("n_nombre2"),
                    resultSet.getString("n_apellido1"),
                    resultSet.getString("n_apellido2"),
                    resultSet.getString("n_ciudad"),
                    resultSet.getString("n_direccion"),
                    resultSet.getLong("o_telefono"),
                    resultSet.getInt("k_idrepcap"),
                    resultSet.getInt("k_idrepactual")
            );
        }
        return null;
    }

    @Override
    public Cliente agregarCliente(DAODataModel<Cliente> cliente) throws Exception{
        Credenciales credenciales = cliente.credenciales();
        Cliente cli = cliente.data();
        OracleConnection connCredentials = new OracleConnection(credenciales.user(),credenciales.password());
        Connection conn = connCredentials.getConn();

        try {
            conn.setAutoCommit(false);

            // Ingresar representante a la base de datos
            String sql = "INSERT INTO clients (k_identificacion, k_correo, n_nombre1, n_nombre2, n_apellido1, n_apellido2, n_ciudad, n_direccion, o_telefono, k_idrepcap, k_idrepactual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, cli.getIdentificacion());
            statement.setString(2, cli.getCorreo());
            statement.setString(3, cli.getNombre1());
            statement.setString(4, cli.getNombre2());
            statement.setString(5, cli.getApellido1());
            statement.setString(6, cli.getApellido2());
            statement.setString(7, cli.getCiudad());
            statement.setString(8,cli.getDireccion());
            statement.setLong(9, cli.getTelefono());
            statement.setLong(10, cli.getIdRepCapto());
            statement.setLong(11, cli.getIdRepActual());

            System.out.println("Ingresando Registro");
            statement.executeUpdate();

            // Crear usuario en la base de datos
            String usuario = "CLI" + cli.getIdentificacion();
            String contraseña = cli.getIdentificacion() + "";
            String crearUsuarioSQL = "CREATE USER " + usuario + " IDENTIFIED BY " + contraseña;
            statement = conn.prepareStatement(crearUsuarioSQL);
            System.out.println("Creando usuario");
            statement.executeUpdate();

            // Ejecutar el procedimiento almacenado
            String asignarRolSQL = "{ call A_R_CLIENTE(?, ?) }";
            CallableStatement callableStatement = conn.prepareCall(asignarRolSQL);
            callableStatement.setString(1, usuario);
            callableStatement.setString(2, "R_CLIENTE");
            System.out.println("Brindando privilegios");
            callableStatement.execute();

            System.out.println("Se ha creado el usuario: " + usuario + " con pass:" + contraseña);
            conn.commit();
        } catch (Exception e){
            conn.rollback();
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return cliente.data();
    }
}
