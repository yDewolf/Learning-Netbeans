package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Anime;
import model.AnimeList;
import model.User;

public class AnimeListDAO {
    public void create(User user, String list_name) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO User_AnimeList (user_id, list_name) VALUES (?, ?)"
            );
            stmt.setInt(1, user.getId());
            stmt.setString(2, list_name);
            
            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    // public void update(Anime anime) {
    //     try {
    //         Connection con = ConnectionFactory.getConnection();
    //         PreparedStatement stmt = con.prepareStatement(
    //                 "UPDATE Animes SET name = ?, description = ? WHERE id = ?"
    //         );
    //         stmt.setString(1, anime.getName());
    //         stmt.setString(2, anime.getDescription());
    //         stmt.setInt(3, anime.getId());
            
    //         ConnectionFactory.execute_statement(con, stmt);
    //         ConnectionFactory.closeConnection(con, stmt);
    //     } catch (SQLException ex) {
    //         System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    //     }
    // }

    // TODO: List by tags 
    public List<AnimeList> list() {
        List<AnimeList> lists = new ArrayList<AnimeList>();
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql =  "SELECT * FROM User_AnimeList";
            PreparedStatement stmt = con.prepareStatement(
                sql
            );
            
            ResultSet result = ConnectionFactory.execute_fetch_statement(con, stmt);
            while (result.next()) {
                AnimeList list = new AnimeList(
                    result.getInt("id"),
                    result.getString("list_name"),
                    result.getInt("user_id")
                );
                lists.add(list);
            }
            ConnectionFactory.closeConnection(con, stmt, result);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return lists;
    }

    public List<AnimeList> get_user_lists(User user) {
        List<AnimeList> lists = new ArrayList<AnimeList>();
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql =  "SELECT * FROM User_AnimeList WHERE user_id = ?";
            PreparedStatement stmt = con.prepareStatement(
                sql
            );
            stmt.setInt(1, user.getId());
            
            ResultSet result = ConnectionFactory.execute_fetch_statement(con, stmt);
            while (result.next()) {
                AnimeList list = new AnimeList(
                    result.getInt("id"),
                    result.getString("list_name"),
                    result.getInt("user_id")
                );
                lists.add(list);
            }
            ConnectionFactory.closeConnection(con, stmt, result);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return lists;
    }
    
    public AnimeList getList(int id) {
        AnimeList list = null;
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql =  "SELECT * FROM User_AnimeList WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(
                sql
            );
            stmt.setInt(1, id);
            
            ResultSet result = ConnectionFactory.execute_fetch_statement(con, stmt);
            while (result.next()) {
                list = new AnimeList(
                        result.getInt("id"),
                        result.getString("list_name"),
                        result.getInt("user_id")
                );
            }
            ConnectionFactory.closeConnection(con, stmt, result);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return list;
    }
    
    public void delete(AnimeList list) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "DELETE FROM User_AnimeList WHERE id = ?"
            );
            stmt.setInt(1, list.getId());
            
            ConnectionFactory.execute_statement(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void add_anime_to_list(AnimeList list, Anime anime, int position) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO User_AnimeList_Anime (list_id, anime_id, position) VALUES (?, ?, ?)"
            );
            stmt.setInt(1, list.getId());
            stmt.setInt(2, anime.getId());
            stmt.setInt(3, position);

            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void update_anime_position(AnimeList list, Anime anime, int position) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE User_AnimeList_Anime SET position = ? WHERE list_id = ? AND anime_id = ?"
            );
            stmt.setInt(1, position);
            stmt.setInt(2, list.getId());
            stmt.setInt(3, anime.getId());

            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public ArrayList<Anime> get_animes_in_list(AnimeList list)  {
        ArrayList<Anime> animes = new ArrayList<Anime>();
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql =  "SELECT A.id, A.name, A.description, AL.position, AR.rating FROM User_AnimeList_Anime AL INNER JOIN Animes A ON AL.anime_id = A.id LEFT JOIN Anime_Ratings AR ON AR.anime_id = A.id AND AR.user_id = ? WHERE list_id = ? ORDER BY AL.position";
            PreparedStatement stmt = con.prepareStatement(
                sql
            );
            stmt.setInt(1, list.getUser_id());
            stmt.setInt(2, list.getId());
            
            ResultSet result = ConnectionFactory.execute_fetch_statement(con, stmt);
            while (result.next()) {
                Anime anime = new Anime(
                    result.getInt("A.id"), 
                    result.getString("A.name"), 
                    result.getString("A.description")
                );
                anime.setPosition(result.getInt("AL.position"));
                anime.setRating(result.getInt("AR.rating"));
                animes.add(anime);
            }
            ConnectionFactory.closeConnection(con, stmt, result);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return animes;
    }

    public void remove_from_list(AnimeList list, Anime anime) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "DELETE FROM User_AnimeList_Anime WHERE list_id = ? AND anime_id = ?"
            );
            stmt.setInt(1, list.getId());
            stmt.setInt(2, anime.getId());

            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public boolean is_anime_in_list(AnimeList list, Anime anime)  {
        boolean is = false;
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql =  "SELECT anime_id FROM User_AnimeList_Anime AL WHERE list_id = ?";
            PreparedStatement stmt = con.prepareStatement(
                sql
            );
            stmt.setInt(1, list.getId());
            
            ResultSet result = ConnectionFactory.execute_fetch_statement(con, stmt);
            while (result.next()) {
                is = result.getInt("anime_id") == anime.getId();
                if (is) { break; }
            }
            ConnectionFactory.closeConnection(con, stmt, result);
        } catch (SQLException ex) {
            System.getLogger(AnimeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return is;
    }
}
