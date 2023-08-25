package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.Customer;

public class CustomerDAO {
         private Connection connection; 

        public void createCustomer(Customer customer) 
        {
        

        String insertQuery = "INSERT INTO customers (id, name, mobile_number) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, customer.id);
            preparedStatement.setString(2, customer.name);
            preparedStatement.setInt(3, customer.mobileNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer created successfully.");
            } else {
                System.out.println("Failed to create customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creating customer.");
        }
        }

        public Customer readCustomer(int id) {
        String selectQuery = "SELECT * FROM customers WHERE id = ?";
        Customer customer = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.id = resultSet.getInt("id");
                customer.name = resultSet.getString("name");
                customer.mobileNumber = resultSet.getInt("mobile_number");
                // You might also want to fetch orders related to the customer
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public void updateCustomer(Customer customer) {
        String updateQuery = "UPDATE customers SET name = ?, mobile_number = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, customer.name);
            preparedStatement.setInt(2, customer.mobileNumber);
            preparedStatement.setInt(3, customer.id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Failed to update customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating customer.");
        }
    }

    public void deleteCustomer(int id) {
        String deleteQuery = "DELETE FROM customers WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Failed to delete customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting customer.");
        }
    }


    
}
