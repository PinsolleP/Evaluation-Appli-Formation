package Dao;

import Database.DatabaseConnection;
import Models.Client;
import Models.Order_;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO permettant de gérer les commandes dans la base de données.
 *
 * <p>Cette classe assure la communication entre les objets {@link Order_}
 * et la table {@code order_} de la base de données.</p>
 */
public class OrderDao {
    /**
     * Crée une nouvelle commande dans la base de données.
     *
     * @param order commande à enregistrer
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public void create(Order_ order) throws SQLException {
        try {
            String sql = """
                 
                    INSERT INTO order_(quantity, date_)
                    VALUES (?, ?)
                    """;

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, order.getQuantity());
                statement.setDate(2, (Date) order.getDate());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
