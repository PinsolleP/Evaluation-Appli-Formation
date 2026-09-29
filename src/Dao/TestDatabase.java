package Dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabase {

    public static void main(String[] args) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Connexion réussie !");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }
}
