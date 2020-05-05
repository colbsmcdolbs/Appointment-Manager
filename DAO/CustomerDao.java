package DAO;

import Models.Address;
import Models.Customer;
import Utils.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author colby
 */
public class CustomerDao {
    
    public static ObservableList<Customer> getAllCustomers() {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String customerQuery = "SELECT * FROM customer;";
            ResultSet customerResult = connection.executeQuery(customerQuery);
            
            ObservableList<Customer> customerList = FXCollections.observableArrayList();
            
            while(customerResult.next()) {
                int custId = customerResult.getInt("customerId");
                String custName = customerResult.getString("customerName");
                int custAddressId = customerResult.getInt("addressId");
                customerList.add(new Customer(custId, custName, custAddressId));
            }
            
            return customerList;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    public static Customer getCustomerById(int customerId) {
        return null;
    }
    
    public static boolean updateCustomerById(int customerId) {
        return true;
    }
    
    public static Address getAddressById(int addressId) {
        return null;
    }
    
    public static boolean updateAddressById(int addressId) {
        return true;
    }
    
}
