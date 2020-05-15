package Controllers;

import DAO.CustomerDao;
import Models.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author colby
 */
public class AllCustomerViewController extends BaseController implements Initializable {

    @FXML private Button returnToDashboardButton, DeleteButton, ModifyButton;
    @FXML private Label errorLabel = new Label();
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> customerIdColumn, customerAddressIdColumn;
    @FXML private TableColumn<Customer, String> customerNameColumn;
    private ObservableList<Customer> customers;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        customers = CustomerDao.getAllCustomers();
        
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTable.setItems(customers);
    }    
    
    @FXML
    private void deleteCustomer() {
        if(customerTable.getSelectionModel().isEmpty()) {
            errorLabel.setText("Error: No Customer Selected");
        }
        else {
            int customerId = customerTable.getSelectionModel().getSelectedItem().getCustomerId();
            CustomerDao.deleteCustomerById(customerId);
            customers = CustomerDao.getAllCustomers();
            customerTable.setItems(customers);
        }
    }
    
    @FXML
    private void modifyCustomer(ActionEvent ae) throws IOException {
        if(customerTable.getSelectionModel().isEmpty()) {
            errorLabel.setText("Error: No Customer Selected");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/EditCustomerView.fxml"));
        Parent addPartParent = loader.load();
        Scene addPartScene = new Scene(addPartParent);
        EditCustomerViewController controller = loader.getController();
        controller.initData(customerTable.getSelectionModel().getSelectedItem());
        Stage window = (Stage) ((Node)ae.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }
    
}
