package com.coursojava.sesion15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
        private final static  String DRIVER_NAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private final static  String URL="jdbc:sqlserver://DGBL2204\\SQLEXPRESS;databaseName=CourseJava;encrypt=true;trustServerCertificate=true;multipleActiveResultSets=true";
        private final static  String USER_NAME="sa";
        private final static  String PASSWORD_NAME="Remigio13579";
        private final static  String TABLE="Language";

    public static void main(String[] args) {

        try {

            Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD_NAME);

            System.out.println("Conexión exitosa a SQL Server");

            //Consult DB
            String sqlCommand= "SELECT * FROM " + TABLE +";";

            //Create new Prepared Statenmet Para la consulta
            var prest = conn.prepareStatement(sqlCommand);

            //Ejecucion
            var rest = prest.executeQuery();

            var metaData = rest.getMetaData();

            int numColumns= metaData.getColumnCount();

//            for (int i = 0; i < numColumns; i++) {
//
//                if(i < numColumns)
//                    System.out.println(metaData.getColumnName(i) + " | ");
//                else
//                    System.out.println(metaData.getColumnName(i) + " | ");
//            }
            System.out.println();
//            while (rest.next()){
//                System.out.println("LenguageId: " + rest.getLong(1));
//                System.out.println("Name: " + rest.getString(2));
//                System.out.println("Ultima Actualización: " + rest.getDate(3));
//            }

             //Extracion de metadatos y datos de la tabla
            //Consult DB
              sqlCommand= "SELECT * FROM " + TABLE +";";

            //Create new Prepared Statenmet Para la consulta
              prest = conn.prepareStatement(sqlCommand);

            //Ejecucion
              rest = prest.executeQuery();

            //Intentar extraer los metadatos de la tabla
              metaData = rest.getMetaData();

              numColumns= metaData.getColumnCount();

            for (int i = 1; i < numColumns; i++) {

                if(i < numColumns)
                    System.out.print(metaData.getColumnName(i) + " | ");
                else
                    System.out.println(metaData.getColumnName(i) );
            }
            System.out.println();
            while (rest.next()){
                for (int i = 1; i < numColumns; i++) {

                    if(i < numColumns)
                        System.out.print(rest.getString(i) + " | ");
                    else
                        System.out.println(rest.getString(i) );
                }
            }

            conn.close();

        }catch (SQLException e){
            System.out.println("Ocurrio un problema creando estableciendo la conexion a SQL Server :" + e.getMessage());
        }


    }
}
