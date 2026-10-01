package User_interface;

import Dao.PersonDao;
import Dao.UserDao;
import Models.Person;
import Models.User_;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserChoice {

    public static boolean controlName() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre login: ");
        String login = scanner.nextLine();

            List<User_> allusers = UserDao.readAll();

            return allusers.stream()
                    .anyMatch(user -> user.getLogin().equalsIgnoreCase(login));
    }
    public static void displayChoice(){

        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        do{
            System.out.println("1. Afficher toutes les formations disponibles.");
            System.out.println("2. Afficher toutes les formations contenant un mot clé.");
            System.out.println("3. Afficher toutes les formations en présentiel ou distanciel.");
            System.out.println("4. Quitter le programme.");
            System.out.println("Votre choix (1-4) :");

            try {
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        System.out.println("Au revoir");
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez saisir entre 1 et 4.");
                }
            } catch (InputMismatchException e){
                System.out.println("Entrée invalide. Veuillez saisir un nombre.");
            }
        }while (choix != 4);

        scanner.close();
    }
}
