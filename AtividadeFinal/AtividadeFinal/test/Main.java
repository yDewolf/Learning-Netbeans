import dao.UserDAO;
import model.User;

public class Main {
    public static void main(String[] args) {
        User user = new User(-1, "CoolUser", "1234");
        UserDAO dao = new UserDAO();
        dao.create(user);

        dao.list();
    }
}
