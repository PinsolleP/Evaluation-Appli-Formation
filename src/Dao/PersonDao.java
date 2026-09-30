package Dao;

import Database.DatabaseConnection;
import Models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static int create(Person person) throws SQLException {
        String sql = """
                INSERT INTO person(first_name, last_name)
                VALUES (?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, person.getFirst_name());
            statement.setString(2, person.getLast_name());

            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    person.setId_person(id);
                    return id;
                }
            }
            throw new SQLException("impossible de créé person");
        }
    }


    /**
     * Recherche une personne à partir de son identifiant.
     *
     * @param id identifiant de la personne recherché
     * @return la personne trouvée, ou {@code null} s'il n'existe pas
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public Person read(int id) throws SQLException {
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

    /**
     * Récupère toutes les personnes dans la base de données.
     *
     * @return liste contenant toutes les personnes
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public List<Person> readAll() throws SQLException {

        String sql = """
                SELECT id_person, first_name, last_name
                FROM person
                """;

        List<Person> persons = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Person pers = new Person(
                        resultSet.getInt("id_person"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );

                persons.add(pers);
            }
        }
        return persons;
    }

    /**
     * Modifie une personne existant dans la base de données.
     *
     * @param person personne contenant les nouvelles informations
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void update(Person person) throws SQLException {

        String sql = """
                UPDATE person
                SET first_name = ?, last_name = ?
                WHERE id_person = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, person.getFirst_name());
            statement.setString(2, person.getLast_name());
            statement.setInt(3, person.getId_person());


            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aucune personne trouvée avec l'ID : " + person.getId_person());
            }
        }
    }

    /**
     * Supprime une personne de la base de données.
     *
     * @param id identifiant de la personne à supprimer
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void delete(int id) throws SQLException {

        String sql = """
                DELETE FROM person
                WHERE id_person = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aucune personne trouvée avec l'ID : " + id);
            }
        }
    }
}

