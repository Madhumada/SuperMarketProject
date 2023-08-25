package Services;

import Entities.Item;
import Repositories.ItemDAO;

public class ItemService {

    public void CreatItem(int price,String name,String description,int quantity)
    {
        try
        {

        Item item = new Item();

        item.price = price;
        item.name = name;
        item.description = description;
        item.quantity = quantity;


        ItemDAO itemDAO = new ItemDAO();
        itemDAO.createItem(item);
        }
        catch(Exception ex)
        {

        }

    }
     

    public Item ReadTtem(int id) throws ClassNotFoundException
    {
                ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.readItem(id);
        return item;

    }
     

    public void UpdateItem(int price,String name,String description,int quantity) throws ClassNotFoundException
    {
        Item item = new Item();

        item.price = price;
        item.name = name;
        item.description = description;
        item.quantity = quantity;


        ItemDAO itemDAO = new ItemDAO();
        itemDAO.updateItem(item);
        
    }
    

    public void DeleteItem(int id)
    {
        
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.deleteItem(id);
        
    }

    
}
