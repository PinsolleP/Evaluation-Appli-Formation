package Models;

import java.util.ArrayList;
import java.util.Date;

public class Order_ {

    private int id_order;
    private int quantity;
    private Date date;
    private Formation formation;
    private Client client;
    private User_ user;
    public ArrayList<Formation> items;
    public ArrayList<Client> clients;

    public Order_(int id_order, int quantity, Date date, Formation formation, Client client, User_ user) {
        this.id_order = id_order;
        this.quantity = quantity;
        this.date = date;
        this.formation = formation;
        this.client = client;
        this.user = user;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public ArrayList<Formation> getItems() {
        return new ArrayList<>(items);
    }

    public ArrayList<Client> getClients() {
        return new ArrayList<>(clients);
    }

    public double getTotal_order(int quantity){
        double total = 0;
        for (Formation item : items){
            total = item.getPrice() * quantity;
        }
        return total;
    }
}
