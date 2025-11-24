import dao.AnimeDAO;
import dao.UserDAO;
import model.Anime;
import model.User;

public class Main {
    public static void main(String[] args) {
        User user = new User(-1, "CoolUser", "1234");
        UserDAO dao = new UserDAO();
        dao.create(user);

        dao.list();

        Anime anime = new Anime(-1, "Sousou No Frieren", "Descrição legal");
        AnimeDAO anime_dao = new AnimeDAO();
        anime_dao.create(anime);

        anime_dao.list();
    }
}
