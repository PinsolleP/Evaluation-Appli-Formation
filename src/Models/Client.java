package Models;

public class Client extends Person{

    private int id_client;
    private String email;
    private String address;
    private int tel_number;

    public Client(int id_person, String first_name, String last_name, int id_client, String email, String address, int tel_number) {
        super(id_person, first_name, last_name);
        this.id_client = id_client;
        this.email = email;
        this.address = address;
        this.tel_number = tel_number;
    }
}
