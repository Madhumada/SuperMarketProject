package Entities;

import java.util.List;

public class Item {
    public int id;
    public int price;
    public String name;
    public String description;
    public int quantity;
    public List<Order> orders;

    public Item(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Item()
    {

    }

}
