package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Anime;
import model.User;

public class AnimeRatingDAO {
    public void add_rating_to_anime(Anime anime, User user, int rating) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Anime_Ratings (user_id, anime_id, rating) VALUES (?, ?, ?)"
            );
            stmt.setInt(1, user.getId());
            stmt.setInt(2, anime.getId());
            stmt.setInt(3, rating);
            
            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void update_anime_rating(Anime anime, User user, int rating) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE Anime_Ratings SET rating = ? WHERE user_id = ? AND anime_id = ?"
            );
            stmt.setInt(1, rating);
            stmt.setInt(2, user.getId());
            stmt.setInt(3, anime.getId());
            
            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public int get_anime_rating(Anime anime, User user) {
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql =  "SELECT rating FROM Anime_Ratings WHERE user_id = ? AND anime_id = ?";
            PreparedStatement stmt = con.prepareStatement(
                sql
            );

            stmt.setInt(1, user.getId());
            stmt.setInt(2, anime.getId());

            ResultSet result = ConnectionFactory.execute_fetch_statement(con, stmt);
            while (result.next()) {
                return result.getInt("rating");
            }
            ConnectionFactory.closeConnection(con, stmt, result);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return -1;
    }
}
