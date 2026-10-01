package Dao;
import Database.DatabaseConnection;
import Models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO permettant de gérer les clients dans la base de données.
 *
 * <p>Cette classe assure la communication entre les objets {@link Client}
 * et la table {@code client} de la base de données.</p>
 */
public class ClientDao {
    /**
     * Crée un nouveau client dans la base de données.
     *
     * @param client client à enregistrer
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void create(Client client) throws SQLException {
        try {
            int id_person = PersonDao.create(client);

            String sql = """
                 
                    INSERT INTO client(email, address, tel_number, id_person)
                    VALUES (?, ?, ?, ?)
                    """;

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, client.getEmail());
                statement.setString(2, client.getAddress());
                statement.setString(3, client.getTel_number());
                statement.setInt(4, id_person);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Échec de la création : aucune ligne insérée dans client.");
                }
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int id_client = resultSet.getInt(1);
                        client.setId_client(id_client);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la création du client : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Recherche un client à partir de son identifiant.
     *
     * @param id identifiant du client recherché
     * @return le client trouvée, ou {@code null} s'il n'existe pas
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public Client read(int id) throws SQLException {
        String sql = """
            
                SELECT c.client, c.email, c.address, c.tel_number
                   p.id_person, p.first_name, p.last_name
            FROM client c
            JOIN person p ON c.id_person = p.id_person
            WHERE c.id_client = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return new Client(
                            resultSet.getInt("id_person"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getInt("id_client"),
                            resultSet.getString("email"),
                            resultSet.getString("address"),
                            resultSet.getString("tel_number")
                    );
                }
            }
        }
        return null;
    }
    /**
     * Récupère tous les clients présents dans la base de données.
     *
     * @return liste contenant tous les clients
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public  List<Client> readAll() throws SQLException {
        String sql = """
            SELECT c.client, c.email, c.address, c.tel_number
                   p.id_person, p.first_name, p.last_name
            FROM client 
            JOIN person p ON c.id_person = p.id_person
            WHERE c.id_client = ?
            """;

        List<Client> clients = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                Client client = new Client(
                        resultSet.getInt("id_person"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("id_client"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("tel_number")
                    );
                clients.add(client);
                }
            }
        return clients;
        }

    /**
     * Modifie un client existant dans la base de données.
     *
     * @param client client contenant les nouvelles informations
     * @throws SQLException si une erreur survient lors de l'accès à la base  de données
     */
    public void update(Client client) throws SQLException{

        String sqlPerson = """
                UPDATE person
                SET first_name = ?, last_name = ?
                WHERE id_person = ?
                """;

        String sqlClient = """
                UPDATE client
                SET email = ?, address = ?, tel_number = ?
                WHERE id_client = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection()){

            try (PreparedStatement psPerson = connection.prepareStatement(sqlPerson)) {
                psPerson.setString(1, client.getFirst_name());
                psPerson.setString(2, client.getLast_name());
                psPerson.setInt(3, client.getId_person());
                psPerson.executeUpdate();
            }

            try (PreparedStatement psUser = connection.prepareStatement(sqlClient)) {
                psUser.setString(1, client.getEmail());
                psUser.setString(2, client.getAddress());
                psUser.setString(3, client.getTel_number());
                psUser.setInt(4, client.getId_client());
                psUser.executeUpdate();
            }
        }
    }

    /**
     * Supprime un client de la base de données.
     *
     * @param id identifiant du client à supprimer
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void delete(int id) throws SQLException {

        String sqlClient = """
            DELETE FROM client
            WHERE id_client = ?
            """;

        String sqlPerson = """
            DELETE FROM person
            WHERE id_person = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection()){


            try(PreparedStatement psUser = connection.prepareStatement(sqlClient)) {
                psUser.setInt(1, id);
                psUser.executeUpdate();
            }

            try (PreparedStatement psPerson = connection.prepareStatement(sqlPerson)) {
                psPerson.setInt(4, id);
                psPerson.executeUpdate();
            }
        }
    }
    }



