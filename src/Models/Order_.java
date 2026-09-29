package Models;

import java.util.Date;

public class Order_ {

    private int id_order;
    private int quantity;
    private Date date;
    private Formation formation;
    private Client client;
    private User_ user;

    public Order_(int id_order, int quantity, Date date, Formation formation, Client client, User_ user) {
        this.id_order = id_order;
        this.quantity = quantity;
        this.date = date;
        this.formation = formation;
        this.client = client;
        this.user = user;
    }
}
