/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Etec
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Bicicleta;
public class BicicletaDAO {
    public void create(Bicicleta b) {
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO bicicleta(codigo, status) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, b.getCodigo());
            stmt.setString(2, b.getStatus());
            stmt.executeUpdate();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public List<Bicicleta> read() {
        List<Bicicleta> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM bicicleta";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Bicicleta b = new Bicicleta();
                b.setId(rs.getInt("id"));
                b.setCodigo(rs.getString("codigo"));
                b.setStatus(rs.getString("status"));
                lista.add(b);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public void update(Bicicleta b) {
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "UPDATE bicicleta SET codigo=?, status=? WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, b.getCodigo());
            stmt.setString(2, b.getStatus());
            stmt.setInt(3, b.getId());
            stmt.executeUpdate();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(Bicicleta b) {
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM bicicleta WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, b.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
