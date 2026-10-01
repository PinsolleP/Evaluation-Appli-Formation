package User_interface;

import Dao.PersonDao;
import Models.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserChoice {

    public static void control_name() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        try {
            List<Person> allpersons = PersonDao.readAll();

            boolean namefound = allpersons.stream()
                    .anyMatch(person -> person.getLast_name().equalsIgnoreCase(name));

            if (namefound) {
                System.out.println("vous êtes déjà inscrit");
            } else {
                System.out.println("vous n'êtes pas encore inscrit");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        scanner.close();
    }
    public void display_choice(){

        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        do{
            System.out.println("1. Afficher toutes les formations disponibles.");
            System.out.println("2. Afficher toutes les formations contenant un mot clé.");
            System.out.println("3. Afficher toutes les formations en présentiel ou distanciel.");
            System.out.println("4. Quitter le programme.");
            System.out.println("Votre choix (1-4) :";

            if (scanner.hasNextInt()){
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix){
                    case 1 :
                        break;
                    case 2 :
                        break;
                    case 3 :
                        break;
                    case 4 :
                        System.out.println("Au revoir");
                    default:
                        System.out.println("Option invalide. Veuillez saisir entre 1 et 4.");
                }
            }else{
                System.out.println("Erreur : Veuillez entrer un chiffre.");
            }
        }while (choix != 4);
        scanner.close();

    }

}
