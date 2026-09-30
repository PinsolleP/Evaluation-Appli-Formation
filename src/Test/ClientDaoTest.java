package Test;


import Dao.ClientDao;
import Dao.PersonDao;
import Models.Client;
import Models.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe permettant de tester les opérations CRUD de {@link ClientDao}.
 */
public class ClientDaoTest {
    /**
     * Point d'entrée du programme de test.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("=== TEST CREATE ===");
        try {
            PersonDao personDao = new PersonDao();
            ClientDao clientDao = new ClientDao();

            Client client = new Client(
                    16,
                    "Testclient",
                    "one",
                    13,
                    "test.client@gmail.com",
                    "12 rue du petit velo 64100 Bayonne",
                    "0684579620"
            );
            personDao.create(client);
            clientDao.create(client);


            System.out.println("Client créé !");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\n=== TEST READ ===");
        try {
            ClientDao clientDao = new ClientDao();
            Client clientTrouve = clientDao.read(1);

            if (clientTrouve != null) {
                System.out.println("client trouvé : " + clientTrouve);
            } else {
                System.out.println("Aucun client trouvé.");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\n=== TEST READALL ===");
        try {
            ClientDao clientDao = new ClientDao();
            List<Client> clients = clientDao.readAll();

            for (Client client : clients) {
                System.out.println(client);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }


    }
}
