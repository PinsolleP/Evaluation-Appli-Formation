package User_interface;

import Dao.FormationDao;
import Dao.UserDao;
import Models.Formation;
import Models.User_;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            System.out.println("==========SELECTION==========");
            System.out.println("1. Afficher toutes les formations disponibles.");
            System.out.println("2. Afficher toutes les formations contenant un mot clé.");
            System.out.println("3. Afficher toutes les formations en présentiel ou distanciel.");
            System.out.println("4. Quitter le programme.");
            System.out.println("Votre choix (1-4) :");

            try {
                choix = Integer.parseInt(scanner.nextLine().trim());

                switch (choix) {
                    case 1:
                        List<Formation> formations = FormationDao.readAll();
                        for (Formation formation : formations){
                            System.out.println(formation.toString());
                        }
                        break;
                    case 2:
                        List<Formation> resultats = SearchByWord();
                        if (resultats.isEmpty()){
                            System.out.println("Aucune formation trouvées avec ce mot clé.");
                        }else {
                            System.out.println("Formation Trouvées :");
                        }
                        for (Formation formation : resultats){
                            System.out.println(formation.toString());
                        }
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }while (choix != 4);

        scanner.close();
    }

    public static List<Formation> SearchByWord() throws SQLException {

        System.out.println("Veuillez saisir votre mot clé :");
        try (Scanner scanner = new Scanner(System.in)) {
            String word = scanner.nextLine().trim().toLowerCase();

            List<Formation> formations = FormationDao.readAll();

            return formations.stream()
                    .filter(f ->
                            (f.getName() != null && f.getName().toLowerCase().contains(word)) ||
                                    (f.getDescription() != null && f.getDescription().toLowerCase().contains(word)) ||
                                    (f.getType() != null && f.getType().toLowerCase().contains(word))
                    )
                    .collect(Collectors.toList());
        }
    }
}

