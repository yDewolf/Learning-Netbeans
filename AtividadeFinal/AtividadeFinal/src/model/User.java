package model;

public class User {
    protected int id;
    protected String username;
    protected String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) {
        if (password.length() < 4 || password.length() > 32) {
            return false;
        }

        this.username = username;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (password.length() < 6 || password.length() > 127) {
            return false;
        }

        this.password = password;
        return true;
    }
}
