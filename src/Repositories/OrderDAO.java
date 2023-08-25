package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Entities.Item;
import Entities.Order;

public class OrderDAO {

    private Connection connection;
    String jdbcUrl = "jdbc:mysql://localhost:3306/Sathi_Pola";
    String username = "root";
    String password = "KGS@madhu1996";

    public void createOrder(Order order) throws ClassNotFoundException {
        String insertOrderQuery = "INSERT INTO orders (time, total) VALUES (?, ?)";

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password); 
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS); 
            preparedStatement.setDate(1, order.time);
            preparedStatement.setDouble(2, order.total);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);
                        insertOrderItems(orderId, order.items);
                        System.out.println("Order created successfully.");
                    }
                }
            } else {
                System.out.println("Failed to create order.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creating order.");
        }
    }

    private void insertOrderItems(int orderId, List<Item> items) throws ClassNotFoundException {
        String insertItemsQuery = "INSERT INTO order_items (order_id, item_id) VALUES (?, ?)";

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password); 
            PreparedStatement preparedStatement = connection.prepareStatement(insertItemsQuery);
            for (Item item : items) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, item.id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creating order items.");
        }
    }

    public Order readOrder(int id) throws ClassNotFoundException {
        String selectQuery = "SELECT * FROM orders WHERE id = ?";
        Order order = null;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.id = resultSet.getInt("id");
                order.time = resultSet.getDate("time");
                order.total = resultSet.getDouble("total");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public void updateOrder(Order order) throws ClassNotFoundException {
        String updateQuery = "UPDATE orders SET time = ?, total = ? WHERE id = ?";

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDate(1, order.time);
            preparedStatement.setDouble(2, order.total);
            preparedStatement.setInt(3, order.id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order updated successfully.");
            } else {
                System.out.println("Failed to update order.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating order.");
        }
    }

    public void deleteOrder(int id) throws ClassNotFoundException {
        String deleteQuery = "DELETE FROM orders WHERE id = ?";

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password); 
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order deleted successfully.");
            } else {
                System.out.println("Failed to delete order.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting order.");
        }
    }




    
}
