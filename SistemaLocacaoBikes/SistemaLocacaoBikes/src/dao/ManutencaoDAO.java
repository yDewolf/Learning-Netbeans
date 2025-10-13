/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Manutencao;

public class ManutencaoDAO {
    public void create(Manutencao m) {
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO manutencao(bicicleta_id, descricao, data) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, m.getBicicletaId());
            stmt.setString(2, m.getDescricao());
            stmt.setDate(3, new java.sql.Date(m.getData().getTime()));
            stmt.executeUpdate();

            String sqlAtualiza = "UPDATE bicicleta SET status='manutenção' WHERE id=?";
            PreparedStatement stmt2 = con.prepareStatement(sqlAtualiza);
            stmt2.setInt(1, m.getBicicletaId());
            stmt2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Manutencao> read() {
        List<Manutencao> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM manutencao";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Manutencao m = new Manutencao();
                m.setId(rs.getInt("id"));
                m.setBicicletaId(rs.getInt("bicicleta_id"));
                m.setDescricao(rs.getString("descricao"));
                m.setData(rs.getDate("data"));
                lista.add(m);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}