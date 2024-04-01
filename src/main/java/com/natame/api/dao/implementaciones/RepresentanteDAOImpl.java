package com.natame.api.dao.implementaciones;

import com.natame.api.dao.interfaces.RepresentanteDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Representante;
import com.natame.api.utils.Credenciales;
import com.natame.api.utils.OracleConnection;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepresentanteDAOImpl implements RepresentanteDAO {
    private final List<Representante> representantes = new ArrayList<>();

    @Override
    public Representante obtenerRepresentante(DAODataModel<Long> id) throws Exception {
        for (Representante representante: representantes){
            if (representante.getIdentificacion() == id.data()){
                return representante;
            }
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
            String sql = "INSERT INTO salesreps (k_identificacion, n_correo, n_nombre1, n_nombre2, n_apellido1, n_apellido2, i_genero, f_fecha_nac, f_fecha_con, o_telefono, n_direccion, k_codigo_Region) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            statement.setInt(12, rep.getCodigoRegion());
            System.out.println("Ingresando Registro");
            statement.executeUpdate();

            // Crear usuario en la base de datos
            String usuario = "REP" + rep.getIdentificacion();
            String contraseña = rep.getIdentificacion() + "";
            String crearUsuarioSQL = "CREATE USER " + usuario + " IDENTIFIED BY " + contraseña;
            statement = conn.prepareStatement(crearUsuarioSQL);
            System.out.println("Creando usuario");
            statement.executeUpdate();

            // Ejecutar el procedimiento almacenado
            String asignarRolSQL = "{ call A_R_CLIENTE(?, ?) }";
            CallableStatement callableStatement = conn.prepareCall(asignarRolSQL);
            callableStatement.setString(1, usuario);
            callableStatement.setString(2, "R_REPRE");
            System.out.println("Brindando privilegios");
            callableStatement.execute();

            System.out.println("Se ha creado el usuario: " + usuario + " con pass:" + contraseña);
            conn.commit();
        } catch (Exception e){
            conn.rollback();
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return representante.data();
    }
}
