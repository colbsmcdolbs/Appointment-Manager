package Models;

/**
 *
 * @author colby
 */
public class Address {
    
    private int addressId;
    private String address1;
    private String address2;
    private int cityId;
    private String postalCode;
    private String phoneNumber;

    public Address(int addressId, String address1, String address2, int cityId, String postalCode, String phoneNumber) {
        this.addressId = addressId;
        this.address1 = address1;
        this.address2 = address2;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    
}
