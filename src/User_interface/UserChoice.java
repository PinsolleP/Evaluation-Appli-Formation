package User_interface;

import Dao.PersonDao;
import Models.Person;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserChoice {

    public static boolean controlName() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre nom: ");
        String name = scanner.nextLine();
        System.out.print("Entrez votre prénom: ");
        String firstname = scanner.nextLine();

            List<Person> allpersons = PersonDao.readAll();

            return allpersons.stream()
                    .anyMatch(person -> person.getLast_name().equalsIgnoreCase(name) &&
                            person.getFirst_name().equalsIgnoreCase(firstname));
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
