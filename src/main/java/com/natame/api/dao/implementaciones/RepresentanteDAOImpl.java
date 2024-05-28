package com.natame.api.dao.implementaciones;

import com.natame.api.dao.interfaces.RepresentanteDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.dto.RepresentanteVista;
import com.natame.api.negocio.entidades.Representante;
import com.natame.api.utils.Credenciales;
import com.natame.api.utils.OracleConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class RepresentanteDAOImpl implements RepresentanteDAO {
    @Override
    public Representante obtenerRepresentante(DAODataModel<Long> id) throws Exception {
        Credenciales credenciales = id.credenciales();
        OracleConnection connCredentials = new OracleConnection(credenciales.user(),credenciales.password());
        Connection conn = connCredentials.getConn();

        String sql = "SELECT * FROM salesreps WHERE k_identificacion = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id.data());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            return new Representante(
                    resultSet.getLong("k_identificacion"),
                    resultSet.getString("n_correo"),
                    resultSet.getString("n_nombre1"),
                    resultSet.getString("n_nombre2"),
                    resultSet.getString("n_apellido1"),
                    resultSet.getString("n_apellido2"),
                    resultSet.getString("i_genero").charAt(0),
                    resultSet.getDate("f_fecha_nac"),
                    resultSet.getDate("f_fecha_con"),
                    resultSet.getLong("o_telefono"),
                    resultSet.getString("n_direccion"),
                    resultSet.getString("i_tipodocumento"),
                    resultSet.getInt("k_codigo_Region"),
                    resultSet.getLong("k_idrepcap"),
                    resultSet.getInt("k_codigocatrep")
            );
        }
        return null;
    }

    @Override
    public Representante agregarRepresentante(DAODataModel<Representante> representante) throws Exception {
        Credenciales credenciales = representante.credenciales();
        Representante rep = representante.data();
        OracleConnection connCredentials = new OracleConnection(credenciales.user(),credenciales.password());
        Connection conn = connCredentials.getConn();

        try {
            conn.setAutoCommit(false);
            // Ingresar representante a la base de datos
            String sql = "INSERT INTO REPRESENTANTE (k_identificacion, n_correo, n_nombre1, n_nombre2, n_apellido1, n_apellido2, i_genero, f_fecha_nac, f_fecha_con, o_telefono, n_direccion, i_tipodocumento, k_codigo_Region, k_idrepcap, k_codigocatrep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, rep.getIdentificacion());
            statement.setString(2, rep.getCorreo());
            statement.setString(3, rep.getNombre1());
            statement.setString(4, rep.getNombre2());
            statement.setString(5, rep.getApellido1());
            statement.setString(6, rep.getApellido2());
            statement.setString(7, rep.getGenero() + "");
            statement.setDate(8, new Date(rep.getFechaNacimiento().getTime()));
            statement.setDate(9, new Date(rep.getFechaContratacion().getTime()));
            statement.setLong(10, rep.getTelefono());
            statement.setString(11, rep.getDiraccion());
            statement.setString(12, rep.getTipoDoc());
            statement.setInt(13, rep.getCodigoRegion());
            statement.setLong(14, rep.getIdRepCap());
            statement.setInt(15, rep.getCodigoCatRep());
            System.out.println("Ingresando Registro");
            statement.executeUpdate();
            System.out.println("Ingresando Registro - EXITOSO");

            statement.execute("ALTER SESSION SET \"_ORACLE_SCRIPT\" = TRUE");

            // Crear usuario en la base de datos
            String usuario = new StringBuilder().append("REP").append(rep.getIdentificacion()).toString();
            String contrasena = new StringBuilder().append("PASS").append(rep.getIdentificacion()).toString();
            String crearUsuarioSQL = new StringBuilder().append("CREATE USER ").append(usuario).append(" IDENTIFIED BY ").append(contrasena).toString();
            statement = conn.prepareStatement(crearUsuarioSQL);
            System.out.println("Creando usuario");
            statement.executeUpdate();
            System.out.println("Creando usuario - EXITOSO");


            for (String priv: OracleConnection.privileges){
                String grantUsuarioSQL = "GRANT " + priv + " TO " + usuario;
                statement = conn.prepareStatement(grantUsuarioSQL);
                statement.executeUpdate();
            }
            System.out.println("Privilegios otorgados");

            System.out.println("Se ha creado el usuario: " + usuario + " con pass:" + contrasena);
            conn.commit();
        } catch (Exception e){
            conn.rollback();
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return representante.data();
    }

    @Override
    public RepresentanteVista obtenerVistaSimple(DAODataModel<String> correo) throws Exception {
        Credenciales credenciales = correo.credenciales();
        OracleConnection connCredentials = new OracleConnection(credenciales.user(),credenciales.password());
        Connection conn = connCredentials.getConn();

        String sql = "SELECT * FROM VISTA_REPRESENTANTE WHERE n_correo = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, correo.data());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            return new RepresentanteVista(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getLong(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }
}
