package com.coursojava.sesion15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
        private final static  String DRIVER_NAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private final static  String URL="jdbc:sqlserver://DGBL2204\\SQLEXPRESS;databaseName=RentMAM-Prod;encrypt=true;trustServerCertificate=true;multipleActiveResultSets=true";
        private final static  String USER_NAME="sa";
        private final static  String PASSWORD_NAME="Remigio13579";

    public static void main(String[] args) {

        try {
            
            Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD_NAME);

            System.out.println("Conexión exitosa a SQL Server");

            conn.close();

        }catch (SQLException e){
            System.out.println("Ocurrio un problema creando estableciendo la conexion a SQL Server :" + e.getMessage());
        }


    }
}
