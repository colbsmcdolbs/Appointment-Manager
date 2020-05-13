package DAO;

import Models.Address;
import Models.Customer;
import Models.User;
import Utils.DBConnection;
import Utils.TimeFunctions;
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
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String customerQuery = "SELECT * FROM customer WHERE customerId=" + customerId + ";";
            ResultSet customerResult = connection.executeQuery(customerQuery);
            
            if(customerResult.next()) {
                int custId = customerResult.getInt("customerId");
                String custName = customerResult.getString("customerName");
                int custAddressId = customerResult.getInt("addressId");
                Customer customer = new Customer(custId, custName, custAddressId);
                return customer;
            }
            
            return null;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    public static boolean verifyCustomerExists(Customer customer) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String customerQuery = "SELECT * FROM customer WHERE customerName='"+ customer.getCustomerName() +"' AND addressId="+ customer.getAddressId() +";";
            ResultSet customerResult = connection.executeQuery(customerQuery);
            
            return customerResult.next();
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static boolean updateCustomerById(Customer customer, User user) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String currentSQLTime = TimeFunctions.getCurrentDateTimeUTCForDatabase();
            String updateCustomerQuery = "UPDATE customer SET customerName='"+ customer.getCustomerName() +"', addressId="+ customer.getAddressId() +", lastUpdate='"+ currentSQLTime +
                                            "', lastUpdateBy='"+ user.getUserName() +"' WHERE customerId="+ customer.getCustomerId() +";"; 
            return connection.execute(updateCustomerQuery);
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static boolean createCustomer(Customer customer, User user) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String currentSQLTime = TimeFunctions.getCurrentDateTimeUTCForDatabase();
            String createUserName = user.getUserName();
            String createCustomerQuery = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES" +
                                        "('" + customer.getCustomerName() + "'," + customer.getAddressId() + "," + 1 + ",'" + currentSQLTime + "','" + createUserName + 
                                        "','" + currentSQLTime + "','" + createUserName + "');"; 
            connection.execute(createCustomerQuery);
            return true;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static boolean verifyAddressExists(Address addressObject) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String customerQuery = "SELECT * FROM address WHERE address='"+ addressObject.getAddress1() +"' AND address2='"+ addressObject.getAddress2() +
                                    "' AND cityId="+ addressObject.getCityId() +" AND postalCode='"+ addressObject.getPostalCode() +"' AND phone='"+ addressObject.getPhoneNumber() +"';";
            ResultSet addressResult = connection.executeQuery(customerQuery);
            
            return addressResult.next();
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static int getAddressId(Address addressObject) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String customerQuery = "SELECT * FROM address WHERE address='"+ addressObject.getAddress1() +"' AND address2='"+ addressObject.getAddress2() +
                                    "' AND cityId="+ addressObject.getCityId() +" AND postalCode='"+ addressObject.getPostalCode() +"' AND phone='"+ addressObject.getPhoneNumber() +"';";
            ResultSet addressResult = connection.executeQuery(customerQuery);
            
            if(addressResult.next()) {
                return addressResult.getInt("addressId");
            }
            return -1;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return -1;
        }
    }
    
    public static Address getAddressById(int addressId) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String customerQuery = "SELECT * FROM address WHERE addressId=" + addressId + ";";
            ResultSet addressResult = connection.executeQuery(customerQuery);
            
            if(addressResult.next()) {
                String address = addressResult.getString("address");
                String address2 = addressResult.getString("address2");
                int cityId = addressResult.getInt("cityId");
                String postalCode = addressResult.getString("postalCode");
                String phone = addressResult.getString("phone");
                Address returnAddress = new Address(addressId, address, address2, cityId, postalCode, phone);
                return returnAddress;
            }
            
            return null;
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    public static boolean updateAddressById(Address address, User user) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String currentSQLTime = TimeFunctions.getCurrentDateTimeUTCForDatabase();
            String updateCustomerQuery = "UPDATE address SET address='"+ address.getAddress1() +"', address2='"+ address.getAddress2() +"',cityId="+
                                            address.getCityId() +", postalCode='"+ address.getPostalCode() +"', phone='"+ address.getPhoneNumber() +"' lastUpdate='"+ currentSQLTime +
                                            "', lastUpdateBy='"+ user.getUserName() +"' WHERE addressId="+ address.getAddressId() +";"; 
            return connection.execute(updateCustomerQuery);
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
    public static boolean createAddress(Address address, User user) {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String currentSQLTime = TimeFunctions.getCurrentDateTimeUTCForDatabase();
            String createUserName = user.getUserName();
            String createAddressQuery = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES" +
                                        "('" + address.getAddress1() + "', '" + address.getAddress2() + "', " + address.getCityId() + ", '"+ address.getPostalCode() +
                                        "', '"+ address.getPhoneNumber() +"', '" + currentSQLTime + "', '" + createUserName + "', '" + currentSQLTime + "', '" + createUserName + "');"; 
            return connection.execute(createAddressQuery);
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
    }
    
}
