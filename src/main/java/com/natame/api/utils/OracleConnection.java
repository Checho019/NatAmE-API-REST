package com.natame.api.utils;

import lombok.Getter;
import java.sql.Connection;
import java.sql.DriverManager;

@Getter
public class OracleConnection {

    private static final String url = "jdbc:oracle:thin:@//localhost:1521/pdb1";
    private final String user;
    private final String password;
    private Connection conn;

    public static String[] privileges = {
            "CONNECT",
            "SELECT ANY TABLE",
            "INSERT ANY TABLE",
            "UPDATE ANY TABLE",
            "DELETE ANY TABLE"
    };

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
