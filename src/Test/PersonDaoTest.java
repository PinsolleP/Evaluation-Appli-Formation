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

        TestCreate();

        TestRead();

        TestReadAll();

        TestUpdate();

        TestDelete();

    }

    private static void TestDelete() {
        System.out.println("\n=== TEST DELETE ===");
        try {
            PersonDao personDao = new PersonDao();

            personDao.delete(18);

            System.out.println("personne 19 supprimé.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void TestUpdate() {
        System.out.println("\n=== TEST UPDATE ===");
        try {
            PersonDao personDao = new PersonDao();
            Person person = personDao.read(19);

            if (person != null) {

                person.setFirst_name("Test modifié");

                personDao.update(person);

                System.out.println("personne modifiée : " + person);

            } else {
                System.out.println("personne introuvable.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void TestReadAll() {
        System.out.println("\n=== TEST READALL ===");
        try {
            PersonDao personDao = new PersonDao();
            List<Person> persons = personDao.readAll();

            for (Person person : persons) {
                System.out.println(person);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void TestRead() {
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

    private static void TestCreate() {
        System.out.println("=== TEST CREATE ===");
        try {
            PersonDao personDao = new PersonDao();

            Person person = new Person(
                    19,
                    "Test",
                    "one"
            );

            personDao.create(person);

            System.out.println("Personne créé !");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

