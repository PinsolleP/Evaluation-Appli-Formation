package Dao;
import Models.Client;
import Models.Person;

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

            try (Connection connection = DatabaseConnection.getConnection()
                 ;
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, client.getEmail());
                statement.setString(2, client.getAddress());
                statement.setString(3, client.getTel_number());
                statement.setInt(4, id_person);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
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
                SELECT id_client, email , address, tel_number, id_person
                FROM client
                WHERE id_client = ?
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
     * @return liste contenant tous les clients
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public  List<Client> readAll() throws SQLException {
        String sql = """
                SELECT id_client, email , address, tel_number, id_person
                FROM client
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
     * @param client client contenant les nouvelles informations
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void update(Client client) throws SQLException{

        String sql = """
                UPDATE client
                SET email = ?, address = ?, tel_number = ?
                WHERE id_client = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, client.getEmail());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getTel_number());
            statement.setInt(4, client.getId_client());

            statement.executeUpdate();
        }

    }
    }

