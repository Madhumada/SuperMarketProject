import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entities.Item;
import Repositories.ItemDAO;
import Services.OrderService;

public class Main {
    public static void main(String[] args)
{

    
   try {
            
            Random random = new Random();

            int min = 10; // Minimum value (inclusive)
            int max = 100; // Maximum value (inclusive)



            // String[] itemNames = {
            //     "Dhal", "books", "pencils", "Biscuits", "icecream",
            //     "maari", "flowe", "cups", "fish", "wine"
            // };
            
            // for (String name : itemNames) {
                
            //     Item item = new Item();
        
            //     item.name = name;
            //     item.description = name;
            //     item.quantity = random.nextInt((max - min) + 1) + min;
            //     item.price = 2000;
               
        
            //     ItemDAO itemRepo = new ItemDAO();
        
            //     itemRepo.createItem(item);
            // }
           
            
            List<Item> orderItems = new ArrayList<>();

            orderItems.add(new Item(1,3));
            orderItems.add(new Item(3,2));

            OrderService newOrder = new OrderService();
            newOrder.placeOrder(orderItems);

    
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class not found: " + e.getMessage());
        }
    
    }
}
