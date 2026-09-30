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
}
