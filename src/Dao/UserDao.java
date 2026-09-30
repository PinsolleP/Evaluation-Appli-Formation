package Dao;
import Models.Client;
import Models.Person;
import Models.User_;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO permettant de gérer les Utilisateur dans la base de données.
 *
 * <p>Cette classe assure la communication entre les objets {@link User_}
 * et la table {@code user_} de la base de données.</p>
 */
public class UserDao {
    /**
     * Crée un nouvel utilisateur dans la base de données.
     *
     * @param user utilisateur à enregistrer
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void create(User_ user) throws SQLException {
        try {
            int id_person = PersonDao.create(user);

            String sql = """
                    INSERT INTO user_(login, password, id_person)
                    VALUES (?, ?, ?)
                    """;

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setInt(4, id_person);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Recherche un utilisateur à partir de son identifiant.
     *
     * @param id identifiant de l'utilisateur recherché
     * @return l'utilisateur trouvé, ou {@code null} s'il n'existe pas
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public User_ read(int id) throws SQLException {
        String sql = """
                SELECT id_user, login, password, id_person
                FROM user_
                WHERE id_user = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return new User_(
                            resultSet.getInt("id_person"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getInt("id_user"),
                            resultSet.getString("login"),
                            resultSet.getString("password")
                    );
                }
            }
        }
        return null;
    }


}
