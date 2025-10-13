/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
    private static final String DATABASE = "locadora_bikes";
    private static final int PORT = 3306;
    
    private static final String URL = "jdbc:mysql://localhost:" + PORT  + "/" + DATABASE;
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
