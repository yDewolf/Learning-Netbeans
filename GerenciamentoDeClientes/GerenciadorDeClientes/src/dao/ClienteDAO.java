package dao;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void salvar(Cliente cliente) {
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;

    try {
        // Comando SQL para inserir dados na tabela clientes
        String sql = "INSERT INTO clientes (nome, email, telefone) VALUES (?, ?, ?)";
        stmt = con.prepareStatement(sql);

        // Define os valores para cada '?' no comando SQL
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setString(3, cliente.getTelefone());

        // Executa o comando SQL
        stmt.executeUpdate();

        JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao salvar cliente: " + ex.getMessage());
    } finally {
        // Fecha a conexão e o statement, independentemente do que aconteceu
        ConnectionFactory.closeConnection(con, stmt);
    }
}
    
    public void atualizar(Cliente cliente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            
            stmt.setInt(4, cliente.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " +
            ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void excluir(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM clientes WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public List<Cliente> listar() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            // Comando SQL para selecionar todos os clientes
            String sql = "SELECT * FROM clientes";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Itera sobre os resultados da consulta
            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));

                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;
    }
    
    
    public List<Cliente> buscarPorNome(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
            stmt = con.prepareStatement(sql);

            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
        }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar clientes: " +
            ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }

}