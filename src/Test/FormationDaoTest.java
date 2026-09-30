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

        System.out.println("\n=== TEST READ ===");
        try {
            FormationDao formationDao = new FormationDao();
            Formation formationTrouve = formationDao.read(1);

            if (formationTrouve != null) {
                System.out.println("formation trouvée : " + formationTrouve);
            } else {
                System.out.println("Aucune formation trouvée.");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }
}