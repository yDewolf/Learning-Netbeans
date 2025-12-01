package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void create(User user) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Users (username, password) VALUES (?, ?)"
            );
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            
            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(UserDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void update(User user) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE Users SET username = ?, password = ? WHERE id = ?"
            );
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId());
            
            ConnectionFactory.execute_statement(con, stmt);
            ConnectionFactory.closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(UserDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    protected List<User> list(String username_query) {
        List<User> users = new ArrayList<User>();
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql =  "SELECT * FROM Users";
            if (!username_query.equals("")) {
                sql += " WHERE username = ?";
                
            }
            PreparedStatement stmt = con.prepareStatement(
                    sql
            );
            
            if (!username_query.equals("")) {
                stmt.setString(1, username_query);
            }
            
            ResultSet result = ConnectionFactory.execute_fetch_statement(con, stmt);
            while (result.next()) {
                User user = new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password")
                );
                users.add(user);
            }
            ConnectionFactory.closeConnection(con, stmt, result);
        } catch (SQLException ex) {
            System.getLogger(UserDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return users;
    }

    public List<User> list() {
        return this.list("");
    }

    public User get_user(String username) {
        List<User> users = this.list(username);
        if (users.isEmpty()) {
            return null;
        }
        
        return users.getLast();
    }
    
    public void delete(User user) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "DELETE FROM Users WHERE id = ?"
            );
            stmt.setInt(1, user.getId());
            
            ConnectionFactory.execute_statement(con, stmt);
        } catch (SQLException ex) {
            System.getLogger(UserDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
