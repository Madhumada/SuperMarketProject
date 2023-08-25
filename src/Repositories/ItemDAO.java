package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.Item;

public class ItemDAO {

    private Connection connection;
    String jdbcUrl = "jdbc:mysql://localhost:3306/Sathi_Pola";
    String username = "root";
    String password = "KGS@madhu1996";

    public void createItem(Item item) throws ClassNotFoundException
    {  
        String insertQuery = "INSERT INTO items (name, description, price, quantity) VALUES (?, ?, ?, ?)";
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
           
            System.out.println("Connected to the database!");
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, item.name);
            preparedStatement.setString(2, item.description);
            preparedStatement.setInt(3, item.price);
            preparedStatement.setInt(4, item.quantity);
            
            preparedStatement.executeUpdate();
            System.out.println("Item created successfully.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error creating item.");
        }

    }
        public Item readItem(int id) throws ClassNotFoundException {

           

        String selectQuery = "SELECT * FROM items WHERE id = ?";
        Item item = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item = new Item();
                item.id = resultSet.getInt("id");
                item.name = resultSet.getString("name");
                item.description = resultSet.getString("description");
                item.price = resultSet.getInt("price");
                item.quantity = resultSet.getInt("quantity");
                // You might also want to fetch orders related to the item
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;

    }
    public void updateItem(Item item) throws ClassNotFoundException {

       
        String updateQuery = "UPDATE items SET name = ?, description = ?, price = ?, quantity = ? WHERE id = ?";

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, item.name);
            preparedStatement.setString(2, item.description);
            preparedStatement.setInt(3, item.price);
            preparedStatement.setInt(4, item.quantity);
            preparedStatement.setInt(5, item.id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item updated successfully.");
            } else {
                System.out.println("Failed to update item.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating item.");
        }
    }
    public void deleteItem(int id) {
        String deleteQuery = "DELETE FROM items WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item deleted successfully.");
            } else {
                System.out.println("Failed to delete item.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting item.");
        }
    }


}