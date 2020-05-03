package Models;

import java.util.Date;

/**
 *
 * @author colby
 */
public class Customer {
    
    private int customerId;
    private String customerName;
    private int addressId;

    public Customer(int customerId, String customerName, int addressId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressId = addressId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAddressId() {
        return addressId;
    }
    
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
