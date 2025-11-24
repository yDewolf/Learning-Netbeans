package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Anime;

public class AnimeDAO {
    public void create(Anime anime) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Animes (name, description) VALUES (?, ?)"
            );
            stmt.setString(1, anime.getName());
            stmt.setString(2, anime.getDescription());
            
            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void update(Anime anime) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE Animes SET name = ?, description = ? WHERE id = ?"
            );
            stmt.setString(1, anime.getName());
            stmt.setString(2, anime.getDescription());
            stmt.setInt(3, anime.getId());
            
            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    // TODO: List by tags 
    public List<Anime> list() {
        List<Anime> animes = new ArrayList();
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql =  "SELECT * FROM Animes";
            PreparedStatement stmt = con.prepareStatement(
                sql
            );
            
            ResultSet result = ConnectionFactory.execute_fetch_statement(con, stmt);
            while (result.next()) {
                Anime anime = new Anime(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("description")
                );
                animes.add(anime);
            }
            ConnectionFactory.closeConnection(con, stmt, result);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return animes;
    }
    
    public void delete(Anime anime) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "DELETE FROM Users WHERE id = ?"
            );
            stmt.setInt(1, anime.getId());
            
            ConnectionFactory.execute_statement(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
