package sistemalocacaobikes.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;


public class ClienteDAO {
    public void create(Cliente c) {
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO cliente(nome, cpf, telefone, email) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getEmail());
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.err.println(e.getCause());
            e.printStackTrace();
        }
    }
    public List<Cliente> read() {
        List<Cliente> lista = new ArrayList<>();
        
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM cliente";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.err.println(e.getCause());
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public void update(Cliente c) {
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "UPDATE cliente SET nome=?, cpf=?, telefone=?, email=? WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getEmail());
            stmt.setInt(5, c.getId());
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.err.println(e.getCause());
            e.printStackTrace();
        }
    }
    public void delete(Cliente c) {
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM cliente WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.err.println(e.getCause());
            e.printStackTrace();
        }
    }
}
