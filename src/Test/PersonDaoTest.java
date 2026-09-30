package Test;


import Dao.PersonDao;
import Models.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe permettant de tester les opérations CRUD de {@link PersonDao}.
 */
public class PersonDaoTest {
    /**
     * Point d'entrée du programme de test.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("=== TEST CREATE ===");
        try {
            PersonDao personDao = new PersonDao();

            Person person = new Person(
                    16,
                    "Test",
                    "one"
            );

            personDao.create(person);

            System.out.println("Personne créé !");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== TEST READ ===");
        try {
            PersonDao personDao = new PersonDao();
            Person personTrouve = personDao.read(1);

            if (personTrouve != null) {
                System.out.println("personne trouvée : " + personTrouve);
            } else {
                System.out.println("Aucune personne trouvée.");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}

