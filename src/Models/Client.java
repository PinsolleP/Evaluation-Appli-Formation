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

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTel_number() {
        return tel_number;
    }

    public void setTel_number(int tel_number) {
        this.tel_number = tel_number;
    }
}
