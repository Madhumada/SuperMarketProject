package Entities;

import java.sql.Date;
import java.util.List;

public class Order {
    public int id;
    public Date time;
    public Double total;
    public List<Item> items; 
    
}
