package Dao;

import Database.DatabaseConnection;
import Models.Client;
import Models.Formation;
import Models.Order_;
import Models.User_;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                 
                    INSERT INTO order_(quantity, date_, id_formation, id_client, id_user)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, order.getQuantity());
                statement.setDate(2, new Date(order.getDate().getTime()));

                statement.setInt(3, order.getFormation().getId_formation());
                statement.setInt(4, order.getClient().getId_client());
                statement.setInt(5, order.getUser().getId_user());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recherche une commande à partir de son identifiant.
     *
     * @param id identifiant de la commande recherchée
     * @return la commande trouvée, ou {@code null} s'il n'existe pas
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public Order_ read(int id) throws SQLException {
        String sql = """
        SELECT o.id_order, o.quantity, o.date_,
               f.id_formation, f.name,
               c.id_client,  pc.id_person AS client_id_person, 
               pc.last_name AS client_nom, pc.first_name AS client_prenom,
               u.id_user, pu.id_person AS user_id_person,
               pu.last_name AS user_nom, pu.first_name AS user_prenom
        FROM order_ o
        JOIN formation f ON o.id_formation = f.id_formation
        JOIN client c ON o.id_client = c.id_client
        JOIN person pc ON c.id_person = pc.id_person
        JOIN user u ON o.id_user = u.id_user
        JOIN person pu ON u.id_person = pu.id_person
        WHERE o.id_order = ?
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    Formation formation = new Formation(
                            resultSet.getInt("id_formation"),
                            resultSet.getString("name")
                    );

                    Client client = new Client(
                            resultSet.getInt("id_client"),
                            resultSet.getInt("id_person"),
                            resultSet.getString("client_prenom"),
                            resultSet.getString("client_nom")
                    );

                    User_ user = new User_(
                            resultSet.getInt("id_user"),
                            resultSet.getInt("user_id_person"),
                            resultSet.getString("user_prenom"),
                            resultSet.getString("user_nom")
                    );

                    return new Order_(
                            resultSet.getInt("id_order"),
                            resultSet.getInt("quantity"),
                            resultSet.getDate("date_"),
                            formation,
                            client,
                            user
                    );
                }
            }
        }
        return null;
    }
    /**
     * Récupère toutes les commandes présentes dans la base de données.
     *
     * @return liste contenant toutes les commandes triées par ID
     * @throws SQLException si une erreur survient lors de l'accès à la base de données
     */
    public List<Order_> readAll() throws SQLException {
        String sql = """
        SELECT o.id_order, o.quantity, o.date_,
               f.id_formation, f.name,
               c.id_client,  pc.id_person AS client_id_person, 
               pc.last_name AS client_nom, pc.first_name AS client_prenom,
               u.id_user, pu.id_person AS user_id_person,
               pu.last_name AS user_nom, pu.first_name AS user_prenom
        FROM order_ o
        JOIN formation f ON o.id_formation = f.id_formation
        JOIN client c ON o.id_client = c.id_client
        JOIN person pc ON c.id_person = pc.id_person
        JOIN user u ON o.id_user = u.id_user
        JOIN person pu ON u.id_person = pu.id_person
        ORDER BY o.id_order
        """;

        List<Order_> orders = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                Formation formation = new Formation(
                        resultSet.getInt("id_formation"),
                        resultSet.getString("name")
                );

                Client client = new Client(
                        resultSet.getInt("id_client"),
                        resultSet.getInt("client_id_person"),
                        resultSet.getString("client_prenom"),
                        resultSet.getString("client_nom")
                );

                User_ user = new User_(
                        resultSet.getInt("id_user"),
                        resultSet.getInt("user_id_person"),
                        resultSet.getString("user_prenom"),
                        resultSet.getString("user_nom")
                );

                Order_ order = new Order_(
                        resultSet.getInt("id_order"),
                        resultSet.getInt("quantity"),
                        resultSet.getDate("date_"),
                        formation,
                        client,
                        user
                );
                orders.add(order);
            }
        }
        return orders;
    }

    /**
     * Modifie une commande existante dans la base de données.
     *
     * @param order commande contenant les nouvelles informations
     * @throws SQLException si une erreur survient lors de l'accès à la base  de données
     */
    public void update(Order_ order) throws SQLException{

        String sql = """
                UPDATE order_
                SET quantity = ?, date_ = ?, id_formation = ?, id_client = ?, id_user = ?
                WHERE id_order = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, order.getQuantity());
                statement.setDate(2, new java.sql.Date(order.getDate().getTime()));
                statement.setInt(3, order.getFormation().getId_formation());
                statement.setInt(4, order.getClient().getId_client());
                statement.setInt(5, order.getUser().getId_user());
                statement.setInt(6, order.getId_order());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aucune commande trouvée avec l'ID : " + order.getId_order());
            }
        }
    }
}
