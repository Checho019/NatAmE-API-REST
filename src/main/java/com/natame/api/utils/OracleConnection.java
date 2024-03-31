package com.natame.api.utils;

import lombok.Getter;
import java.sql.Connection;
import java.sql.DriverManager;

@Getter
public class OracleConnection {

    private static final String url = "jdbc:oracle:thin:@//localhost:1521/pdb";
    private final String user;
    private final String password;
    private Connection conn;

    public OracleConnection(String user, String password) throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        this.user = user;
        this.password = password;

        crearConexion();
    }

    private void crearConexion() throws Exception{
        this.conn = DriverManager.getConnection(url,user,password);
    }

}
