package Controllers;

import DAO.CountryCityDao;
import DAO.CustomerDao;
import Models.Address;
import Models.City;
import Models.Customer;
import Utils.SessionManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author colby
 */
public class AddCustomerViewController extends BaseController implements Initializable {

    @FXML private TextField customerNameTextField = new TextField();
    @FXML private TextField addressTextField = new TextField();
    @FXML private TextField address2TextField = new TextField();
    @FXML private TextField postalCodeTextField = new TextField();
    @FXML private TextField phoneTextField = new TextField();
    @FXML private Label ErrorLabel = new Label();
    @FXML private Button exitButton = new Button();
    
    @FXML private Alert confirmationAlert = new Alert(AlertType.INFORMATION);
    
    @FXML private TableView<City> cityTable;
    @FXML private TableColumn<City, Integer> cityIdColumn;
    @FXML private TableColumn<City, String> cityNameColumn;
    private ObservableList<City> cities;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cities = CountryCityDao.getAllCities();
        
        cityIdColumn.setCellValueFactory(new PropertyValueFactory<>("cityId"));
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        cityTable.setItems(cities);
        
        confirmationAlert.setTitle("Successful Addition");
        confirmationAlert.setHeaderText("Successful!");
    }    
    
    @FXML
    private void addCustomer() {
        String result = validateCustomer();
        if(!result.equals("Ok")) {
            ErrorLabel.setText(result);
        }
        else {
            exitButton.fire();
        }
    }
    
    private String validateCustomer() {
        if(this.customerNameTextField.getText().isEmpty()) {
            return errorGeneratorMissing("Customer Name");
        }
        if(this.addressTextField.getText().isEmpty()) {
            return errorGeneratorMissing("Address");
        }
        if(this.address2TextField.getText().isEmpty()) {
            return errorGeneratorMissing("Address 2");
        }
        if(this.postalCodeTextField.getText().isEmpty()) {
            return errorGeneratorMissing("Postal Code");
        }
        if(this.phoneTextField.getText().isEmpty()) {
            return errorGeneratorMissing("Phone");
        }
        if(this.cityTable.getSelectionModel().isEmpty()) {
            return errorGeneratorMissing("City");
        }
        
        //validate all address portions are ok
        //validate all customer fields are ok
        if(this.customerNameTextField.getText().length() > 45) {
            return errorGeneratorExceedsLength("Customer Name", 45);
        }
        if(this.addressTextField.getText().length() > 50) {
            return errorGeneratorExceedsLength("Address", 50);
        }
        if(this.address2TextField.getText().length() > 50) {
            return errorGeneratorExceedsLength("Address 2", 50);
        }
        if(this.postalCodeTextField.getText().length() > 10) {
            return errorGeneratorExceedsLength("Postal Code", 10);
        }
        if(this.phoneTextField.getText().length() > 20) {
            return errorGeneratorExceedsLength("Phone", 20);
        }
        //check for duplicate address
        Address tempAdd = new Address(-1, addressTextField.getText(), address2TextField.getText(),this.cityTable.getSelectionModel().getSelectedItem().getCityId(), postalCodeTextField.getText(), phoneTextField.getText());
        if(!CustomerDao.verifyAddressExists(tempAdd)) {
            CustomerDao.createAddress(tempAdd, SessionManager.getSessionUser());
        }
        int tempAddressId = CustomerDao.getAddressId(tempAdd);
        if(tempAddressId == -1) {
            return "Error: Cannot find address.";
        }
        
        //add customer to db
        Customer tempCustomer = new Customer(-1, this.customerNameTextField.getText(), tempAddressId);
        if(CustomerDao.verifyCustomerExists(tempCustomer)) {
            return "Error: Specified Customer Already Exists in Database";
        }
        if(!CustomerDao.createCustomer(tempCustomer, SessionManager.getSessionUser())) {
            return "Error: Could not add customer to the Database";
        }
        
        //display a success alert
        confirmationAlert.setContentText("Customer: "+ tempCustomer.getCustomerName() +" successfully added to Database.");
        confirmationAlert.showAndWait();
        
        return "Ok";
    }
    
    private String errorGeneratorMissing(String field) {
        return "Error: '"+ field +"' is a required field";
    }
    
    private String errorGeneratorExceedsLength(String field, int maxLength) {
        return "Error: '"+ field +"' is too long. Max Length: "+ maxLength;
    }
    
}
