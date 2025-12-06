package org.example.infraestructura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexionBD {

    //CREATE TABLE IF NOT EXISTS estudiantes (
    //              id INT AUTO_INCREMENT PRIMARY KEY,
    //                nombre VARCHAR(100),
    //                carrera VARCHAR(100)
    //            );

    // BD en archivo: crea ~/integrador_bd.mv.db en tu usuario
    private String url = "jdbc:h2:~/integrador_bd";
    private String usuario = "sa";
    private String contraseña = "";


    public Connection conectar(){
        try{
            Connection conexionAMiBD=DriverManager.getConnection(url,usuario,contraseña);
            System.out.println("Exito conectandonos a la BD");
            return conexionAMiBD;

        }catch(Exception error){
            System.out.println("error en la conexion "+error.getMessage());
            return null;
        }
    }

    public void crear_tabla_estudiante(){
        String sql= """
                CREATE TABLE IF NOT EXISTS estudiantes (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                    nombre VARCHAR(100),
                                    carrera VARCHAR(100)
                                );
                
                
                """;
        try(
                Connection conexion=conectar();
                Statement chismeSQL=conexion.createStatement()){

            chismeSQL.execute(sql);
            System.out.println("Tabla de estudiantes lista");

        }catch(Exception error){
            System.out.println("fallamos creando la tabla "+error.getMessage());

        }
    }





}
