package Dao;

import Models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * DAO permettant de gérer les personnes dans la base de données.
 *
 * <p>Cette classe assure la communication entre les objets {@link Person}
 * et la table {@code person} de la base de données.</p>
 */

public class PersonDao {
    /**
     * Crée une nouvelle personne dans la base de données.
     *
     * @param person personne à enregistrer
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void create(Person person) throws SQLException {
        String sql = """
                INSERT INTO person(first_name, last_name)
                VALUES (?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, person.getFirst_name());
            statement.setString(2, person.getLast_name());

            statement.executeUpdate();
        }
    }

    /**
     * Recherche une personne à partir de son identifiant.
     *
     * @param id identifiant de la personne recherché
     * @return la personne trouvé, ou {@code null} s'il n'existe pas
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public Person findById(int id) throws SQLException {
        String sql = """
                SELECT id_person, first_name, last_name
                FROM person
                WHERE id_person = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return new Person(
                            resultSet.getInt("id_person"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")
                    );
                }
            }
        }
        return null;
    }
}
