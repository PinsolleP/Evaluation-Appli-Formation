package Models;

public class Formation {

    private int id_formation;
    private String name;
    private String description;
    private int duration;
    private String type;
    private double price;

    public Formation(int id_formation, String name, String description, int duration, String type, double price) {
        this.id_formation = id_formation;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.type = type;
        this.price = price;
    }
}
