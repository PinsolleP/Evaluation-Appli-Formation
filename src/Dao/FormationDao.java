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
            throw new SQLException("impossible de créé person");
        }
    }
}
