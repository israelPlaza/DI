/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Class es/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.sql.*;

public class JDBC {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/concesionario";    
        String username = "root"; // Asegúrate de poner tu usuario de la base de datos
        String password = ""; // Asegúrate de poner tu contraseña de la base de datos

        ResultSet resultSet = null;

        // Bloque try con recursos para manejo de conexión a la base de datos
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            // Crear y ejecutar una consulta SQL SELECT.
            String selectSql = "SELECT * FROM coches";
            resultSet = statement.executeQuery(selectSql);

            // Imprimir los resultados de la consulta
            while (resultSet.next()) {
                System.out.println(resultSet.getString(5) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Mostrar el error en caso de que ocurra una excepción SQL
        }
    }
}

