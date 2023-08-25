package Services;

import java.util.List;

import Entities.Item;
import Entities.Order;
import Repositories.ItemDAO;
import Repositories.OrderDAO;

public class OrderService {
    
    public boolean placeOrder(List<Item> items) throws ClassNotFoundException 
    {
        ItemDAO itemDAO = new ItemDAO();
        double totalOrderPrice = 0;

        // Checking availability and calculating the total price
        for (Item item : items) {
            Item existingItem = itemDAO.readItem(item.id);
            if (existingItem == null || existingItem.quantity < item.quantity) {
                System.out.println("Item " + item.name + " is not available in the required quantity.");
                return false; // One of the items is not available in the required quantity
            }

            totalOrderPrice += existingItem.price * item.quantity;
        }

        // Updating item quantities
        for (Item item : items) {
            Item existingItem = itemDAO.readItem(item.id);
            existingItem.quantity -= item.quantity;
            itemDAO.updateItem(existingItem);
        }

        // Creating order
        Order order = new Order();
        order.total = totalOrderPrice;
        order.time = new java.sql.Date(System.currentTimeMillis());
        order.items = items;

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.createOrder(order);

        System.out.println("Order placed successfully!");
        return true;
    }
}

