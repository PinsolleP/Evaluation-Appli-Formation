package Models;
/**
 * Classe Représentant un client
 * hérite de la classe Person
 */
public class Client extends Person{
    /**
     * attributs
     */
    private int id_client;
    private String email;
    private String address;
    private String tel_number;
    /**
     * Constructeur
     */
    public Client(int id_person, String first_name, String last_name, int id_client, String email, String address, String tel_number) {
        super(id_person, first_name, last_name);
        this.id_client = id_client;
        this.email = email;
        this.address = address;
        this.tel_number = tel_number;
    }

    public Client( int id_client, int id_person, String first_name, String last_name) {
        super(id_person, first_name, last_name);
        this.id_client = id_client;
    }
    /**
     * Getter et setter
     */
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

        if ( email == null){
            throw new IllegalArgumentException("Le mail ne peut pas être null.");
        }

        String regexPattern = "^(.+)@(\\S+)$";

        if (!email.matches(regexPattern)){
            throw new IllegalArgumentException(("Le mail saisi est invalide"));
        }

        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {

        if ( tel_number == null){
            throw new IllegalArgumentException("Le numéro de téléphone ne peut pas être null.");
        }

        String cleaned = tel_number.replaceAll("[\\s\\-\\.]", "");
        String regex = "^(0|\\+33|0033)[1-9][0-9]{8}$";

        if (!cleaned.matches(regex)){
            throw new IllegalArgumentException("Le numéro de téléphone est invalide.");
        }
        this.tel_number = tel_number;
    }
    @Override
    public String toString(){
        return super.toString() +
                ", idClient=" + id_client +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + tel_number + '\'' +
                '}';
    }
}
