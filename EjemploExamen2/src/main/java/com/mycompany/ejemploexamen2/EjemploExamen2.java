/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemploexamen2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author isra
 */
public class EjemploExamen2 {

    public static void main(String[] args) throws SQLException {
     
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/alumnnos", "root", "1985")) {
            System.out.println("Tes has conectado a la base de datos alumnos");
            try (Statement stmt = conn.createStatement()) {
                //execute query
                try (ResultSet rs = stmt.executeQuery("SELECT 'Hello World!'")) {
                    //position result to first
                    rs.first();
                    System.out.println(rs.getString(1)); //result is "Hello World!"
                }
            }
        }
    }
}
