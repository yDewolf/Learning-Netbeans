package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/AnimeList";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão com o banco de dados: ", ex);
        }
    }
    
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao fechar a conexão: " + ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao fechar o statement: " + ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao fechar o result set: " + ex);
        }
    }


    public static void execute_statement(Connection con, PreparedStatement stmt) {
        try {
            stmt.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar statement: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public static ResultSet execute_fetch_statement(Connection con, PreparedStatement stmt) {
        ResultSet result = null;
        try {
            result = stmt.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar statement: " + ex.getMessage());
        }
        
        return result;
    }
}