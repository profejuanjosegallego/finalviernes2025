package org.example.controladores;

import org.example.infraestructura.ConexionBD;
import org.example.modelos.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteControlador {


    //Ojo pues muchachos, aca vamos a programar el CRUD

    public void usuarioPOST(Estudiante estudiante){
        String consultaSQL = "INSERT INTO estudiantes(nombre, carrera) VALUES(?,?)";

        try (Connection conexion = new ConexionBD().conectar();
             PreparedStatement parceroSQL = conexion.prepareStatement(consultaSQL)) {


            parceroSQL.setString(1, estudiante.getNombre());
            parceroSQL.setString(2, estudiante.getCarrera());
            parceroSQL.executeUpdate();

            System.out.println("😊 ¡Éxito guardando los datos!");

        } catch(Exception error) {
            System.out.println("❌ Fallamos guardando: " + error.getMessage());
        }
    }




}
