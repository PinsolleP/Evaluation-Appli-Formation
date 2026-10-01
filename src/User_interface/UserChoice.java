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


}
