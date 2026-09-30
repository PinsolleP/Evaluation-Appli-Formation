package Test;


import Dao.PersonDao;
import Models.Person;

import java.sql.SQLException;

/**
 * Classe permettant de tester les opérations CRUD de {@link PersonDao}.
 */
public class PersonDaoTest {
    /**
     * Point d'entrée du programme de test.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args){

        try {
            PersonDao personDao = new PersonDao();

            System.out.println("=== TEST CREATE ===");

            Person person = new Person(
                    16,
                    "Test",
                    "one"
            );

            personDao.create(person);

            System.out.println("Personne créé !");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
