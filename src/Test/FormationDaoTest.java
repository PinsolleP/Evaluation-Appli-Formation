package Test;


import Dao.FormationDao;
import Dao.PersonDao;
import Models.Formation;
import Models.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe permettant de tester les opérations CRUD de {@link FormationDao}.
 */
public class FormationDaoTest {
    /**
     * Point d'entrée du programme de test.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("=== TEST CREATE ===");
        try {
            FormationDao formationDao = new FormationDao();

            Formation formation = new Formation(
                    11,
                    "Test",
                    "description",
                    10,
                    "presentiel",
                    100.00
            );

            formationDao.create(formation);

            System.out.println("Formation créé !");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}