package Dao;
import Database.DatabaseConnection;
import Models.Formation;
import Models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO permettant de gérer les formations dans la base de données.
 *
 * <p>Cette classe assure la communication entre les objets {@link Formation}
 * et la table {@code formation} de la base de données.</p>
 */
public class FormationDao {
    /**
     * Crée une nouvelle formation dans la base de données.
     *
     * @param formation formation à enregistrer
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public static int create(Formation formation) throws SQLException {
        String sql = """
                INSERT INTO formation(name, description, duration, type, price)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, formation.getName());
            statement.setString(2, formation.getDescription());
            statement.setInt(3, formation.getDuration());
            statement.setString(4, formation.getType());
            statement.setDouble(5, formation.getPrice());

            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    formation.setId_formation(id);
                    return id;
                }
            }
            throw new SQLException("impossible de créé une formation");
        }
    }
    /**
     * Recherche une formation à partir de son identifiant.
     *
     * @param id identifiant de la formation recherchée
     * @return la formation trouvée, ou {@code null} s'il n'existe pas
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public Formation read(int id) throws SQLException {
        String sql = """
                SELECT id_formation, name, description, duration, type, price
                FROM formation
                WHERE id_formation = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return new Formation(
                            resultSet.getInt("id_formation"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("duration"),
                            resultSet.getString("type"),
                            resultSet.getInt("price")
                    );
                }
            }
        }
        return null;
    }
    /**
     * Récupère toutes les formations dans la base de données.
     *
     * @return liste contenant toutes les formations
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public List<Formation> readAll() throws SQLException {

        String sql = """
                SELECT id_formation, name, duration, description, type, price
                FROM formation
                """;

        List<Formation> formations = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Formation formation = new Formation(
                        resultSet.getInt("id_formation"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("duration"),
                        resultSet.getString("type"),
                        resultSet.getInt("price")
                );

                formations.add(formation);
            }
        }
        return formations;
    }
    /**
     * Modifie une formation existante dans la base de données.
     *
     * @param formation formation contenant les nouvelles informations
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void update(Formation formation) throws SQLException {

        String sql = """
                UPDATE formation
                SET name = ?, description = ?, duration = ?, type = ?, price = ?
                WHERE id_formation = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, formation.getName());
            statement.setString(2, formation.getDescription());
            statement.setInt(3, formation.getDuration());
            statement.setString(4, formation.getType());
            statement.setDouble(5, formation.getPrice());
            statement.setInt(6, formation.getId_formation());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aucune formation trouvée avec l'ID : " + formation.getId_formation());
            }
        }
    }
    /**
     * Supprime une formation de la base de données.
     *
     * @param id identifiant de la formation à supprimer
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void delete(int id) throws SQLException {

        String sql = """
                DELETE FROM formation
                WHERE id_formation = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aucune formation trouvée avec l'ID : " + id);
        }
    }
}
