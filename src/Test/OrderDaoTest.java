package Test;

import Dao.FormationDao;
import Dao.OrderDao;
import Models.Client;
import Models.Formation;
import Models.Order_;
import Models.User_;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Classe permettant de tester les opérations CRUD de {@link OrderDao}.
 */
public class OrderDaoTest {
    public static void main(String[] args) {
        /**
        * Point d'entrée du programme de test.
        *
        * @param args arguments de la ligne de commande
        */
        System.out.println("=== TEST CREATE ===");
        try {
            OrderDao orderDao = new OrderDao();

            Formation formation = new Formation(9, null, null, 0, null, 0.0);
            Client client = new Client(7, 8, null, null);
            User_ user = new User_(2, 14, null, null);

            Order_ order = new Order_(
                    0,
                    3,
                    Date.valueOf(LocalDate.now()),
                    formation,
                    client,
                    user
            );

            orderDao.create(order);

            System.out.println("order créé avec l'ID " + order.getId_order());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== TEST READ ===");
        try {
            OrderDao orderDao = new OrderDao();
            Order_ orderTrouve = orderDao.read(1);

            if (orderTrouve != null) {
                System.out.println("commande trouvée : " + orderTrouve);
            } else {
                System.out.println("Aucune commande trouvée.");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
