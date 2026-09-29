package Models;

import java.util.ArrayList;
/**
 * Classe Représentant une personne
 */
public class Person {
    /**
     * attributs
     */
    private int id_person;
    private String first_name;
    private String last_name;
    /**
     * Constructeur
     */
    public Person(int id_person, String first_name, String last_name) {
        this.id_person = id_person;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    /**
     * Getter et setter
     */
    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    /**
     * Méthode toString
     * retourne "prénom nom"
     */
    @Override
    public String toString() {
        return this.first_name + " " + this.last_name;
    }
}
