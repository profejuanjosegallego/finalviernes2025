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

    public void guardar(Estudiante estudiante){
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

    public List<Estudiante> buscarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";

        try (Connection conexion = new ConexionBD().conectar();
             PreparedStatement parceroSQL = conexion.prepareStatement(sql);
             ResultSet resultado = parceroSQL.executeQuery()) {

            while (resultado.next()) {
                Estudiante estudiante = new Estudiante(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("carrera")
                );
                lista.add(estudiante);
            }

        } catch (Exception e) {
            System.out.println("❌ Error buscando todos: " + e.getMessage());
        }

        return lista;
    }

    public Estudiante buscarPorID(int id) {
        String sql = "SELECT * FROM estudiantes WHERE id=?";

        try (Connection conexion = new ConexionBD().conectar();
             PreparedStatement parceroSQL = conexion.prepareStatement(sql)) {

            parceroSQL.setInt(1, id);
            ResultSet resultado = parceroSQL.executeQuery();

            if (resultado.next()) {
                return new Estudiante(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("carrera")
                );
            }

        } catch (Exception e) {
            System.out.println("❌ Error buscando por ID: " + e.getMessage());
        }

        return null;
    }

    public List<Estudiante> buscarPorNombre(String nombre) {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes WHERE nombre LIKE ?";

        try (Connection conexion = new ConexionBD().conectar();
             PreparedStatement parceroSQL = conexion.prepareStatement(sql)) {

            parceroSQL.setString(1, "%" + nombre + "%");
            ResultSet resultado = parceroSQL.executeQuery();

            while (resultado.next()) {
                lista.add(new Estudiante(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("carrera")
                ));
            }

        } catch (Exception e) {
            System.out.println("❌ Error buscando por nombre: " + e.getMessage());
        }

        return lista;
    }

    public void actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiantes SET nombre=?, carrera=? WHERE id=?";

        try (Connection conexion = new ConexionBD().conectar();
             PreparedStatement parceroSQL = conexion.prepareStatement(sql)) {

            parceroSQL.setString(1, estudiante.getNombre());
            parceroSQL.setString(2, estudiante.getCarrera());
            parceroSQL.setInt(3, estudiante.getId());

            int filas = parceroSQL.executeUpdate();

            if (filas > 0)
                System.out.println("✔️ Estudiante actualizado");
            else
                System.out.println("⚠️ No existe estudiante con ese ID");

        } catch (Exception e) {
            System.out.println("❌ Error actualizando: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM estudiantes WHERE id=?";

        try (Connection conexion = new ConexionBD().conectar();
             PreparedStatement parceroSQL = conexion.prepareStatement(sql)) {

            parceroSQL.setInt(1, id);
            int filas = parceroSQL.executeUpdate();

            if (filas > 0)
                System.out.println("🗑️ Estudiante eliminado");
            else
                System.out.println("⚠️ No existe estudiante con ese ID");

        } catch (Exception e) {
            System.out.println("❌ Error eliminando: " + e.getMessage());
        }
    }







}
