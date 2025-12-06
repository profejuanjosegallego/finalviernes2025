package org.example;

import org.example.controladores.EstudianteControlador;
import org.example.infraestructura.ConexionBD;
import org.example.modelos.Estudiante;
import org.h2.tools.Server;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{

        // Consola H2 en http://localhost:8082
        Server.createWebServer("-webAllowOthers", "-webPort", "8082").start();

        //Activando la conexion y creando las tablas
        ConexionBD conexion = new ConexionBD();
        conexion.crear_tabla_estudiante();


        //pedir datos
        //scanner




    }
}