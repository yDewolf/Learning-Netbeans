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
    // Outros métodos (listar, atualizar, excluir) virão aqui...
}