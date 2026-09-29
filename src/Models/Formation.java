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

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        if ( price < 0 ){
            throw new IllegalArgumentException("Le prix ne peux pas être négatif");
        }
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
