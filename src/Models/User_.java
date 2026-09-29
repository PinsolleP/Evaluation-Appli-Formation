package Models;

public class User_ extends Person{

    private int id_user;
    private String login;
    private String password;

    public User_(int id_person, String first_name, String last_name, int id_user, String login, String password) {
        super(id_person, first_name, last_name);
        this.id_user = id_user;
        this.login = login;
        this.password = password;

    }
}


