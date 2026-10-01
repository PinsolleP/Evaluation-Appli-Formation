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
import java.util.List;

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
        TestCreate();

        TestRead();

        TestReadAll();

        TestUpdate();

        TestDelete();
    }

    private static void TestDelete() {
        System.out.println("\n=== TEST DELETE ===");
        try {
            OrderDao orderDao = new OrderDao();

            orderDao.delete(1);

            System.out.println("commande 1 supprimée.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void TestUpdate() {
        System.out.println("\n=== TEST UPDATE ===");
        try {
            OrderDao orderDao = new OrderDao();
            Order_ order = orderDao.read(1);

            if (order != null) {

                order.setQuantity(8);

                orderDao.update(order);

                System.out.println("commande modifiée : " + order);

            } else {
                System.out.println("commande introuvable.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void TestReadAll() {
        System.out.println("\n=== TEST READALL ===");
        try {
            OrderDao orderDao = new OrderDao();
            List<Order_> orders = orderDao.readAll();

            for (Order_ order : orders) {
                System.out.println(order);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void TestRead() {
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

    private static void TestCreate() {
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
    }
}

