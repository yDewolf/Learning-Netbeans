/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Locacao;

public class LocacaoDAO {

    public void alugar(Locacao l) {
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO locacao(cliente_id, bicicleta_id, data_inicio, data_fim, status) VALUES (?, ?, ?, ?, 'ativa')";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, l.getClienteId());
            stmt.setInt(2, l.getBicicletaId());
            stmt.setTimestamp(3, new
            java.sql.Timestamp(l.getDataInicio().getTime()));
            stmt.setTimestamp(4, new
            java.sql.Timestamp(l.getDataFim().getTime()));
            stmt.executeUpdate();

            String sqlAtualiza = "UPDATE bicicleta SET status='locada' WHERE id=?";
            PreparedStatement stmt2 = con.prepareStatement(sqlAtualiza);
            stmt2.setInt(1, l.getBicicletaId());
            stmt2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void devolver(int locacaoId, int bicicletaId) {
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "UPDATE locacao SET status='finalizada' WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, locacaoId);
            stmt.executeUpdate();
            
            String sqlAtualiza = "UPDATE bicicleta SET status='disponível' WHERE id=?";
            PreparedStatement stmt2 = con.prepareStatement(sqlAtualiza);
            stmt2.setInt(1, bicicletaId);
            stmt2.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Locacao> read() {
        List<Locacao> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM locacao";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Locacao l = new Locacao();
                l.setId(rs.getInt("id"));
                l.setClienteId(rs.getInt("cliente_id"));
                l.setBicicletaId(rs.getInt("bicicleta_id"));
                l.setDataInicio(rs.getTimestamp("data_inicio"));
                l.setDataFim(rs.getTimestamp("data_fim"));
                l.setStatus(rs.getString("status"));
                lista.add(l);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
