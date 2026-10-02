package Business;

import User_interface.UserChoice;

import java.sql.SQLException;

public class AppliFormation {
    public static void main(String[] args) throws SQLException {

        boolean register = UserChoice.controlName();
        if (register){
            UserChoice.displayChoice();
        }
    }
}
