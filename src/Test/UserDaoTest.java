package Test;

import Dao.ClientDao;
import Dao.PersonDao;
import Dao.UserDao;
import Models.Client;
import Models.User_;

import java.sql.SQLException;
import java.util.List;
/**
 * Classe permettant de tester les opérations CRUD de {@link UserDao}.
 */
public class UserDaoTest {
    /**
     * Point d'entrée du programme de test.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("=== TEST CREATE ===");
        try {
            PersonDao personDao = new PersonDao();
            UserDao userDao = new UserDao();

            User_ user = new User_(
                    16,
                    "Testuser",
                    "one",
                    4,
                    "paulpinsolle",
                    "fegehzgqd14"
            );
            personDao.create(user);
            userDao.create(user);


            System.out.println("User créé !");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\n=== TEST READ ===");
        try {
            UserDao userDao = new UserDao();
            User_ userTrouve = userDao.read(1);

            if (userTrouve != null) {
                System.out.println("user trouvé : " + userTrouve);
            } else {
                System.out.println("Aucun utilisateur trouvé.");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\n=== TEST READALL ===");
        try {
            UserDao userDao = new UserDao();
            List<User_> users = userDao.readAll();

            for (User_ user : users) {
                System.out.println(user);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\n=== TEST UPDATE ===");
        try {
            UserDao userDao = new UserDao();
            User_ user = userDao.read(4);

            if (user != null) {

                user.setLogin("modifielogin");

                userDao.update(user);

                System.out.println("utilisateur modifié : " + user);

            } else {
                System.out.println("utilisateur introuvable.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
