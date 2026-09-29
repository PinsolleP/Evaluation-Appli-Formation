package Dao;

import Models.Person;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * classe permettant de Tester la connexion avec la base de données.
 *
 * Cette classe assure la communication avec la BDD
 *
 */
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
