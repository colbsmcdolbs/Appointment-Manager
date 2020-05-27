package Controllers;

import DAO.AppointmentDao;
import DAO.CustomerDao;
import Interfaces.IIndexLoop;
import Models.Appointment;
import Models.Customer;
import Utils.SessionManager;
import Utils.TimeFunctions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
public class EditAppointmentViewController implements Initializable {

    @FXML private ChoiceBox locationChoiceBox, timeChoiceBox, contactChoiceBox, typeChoiceBox;
    @FXML private DatePicker appointmentDatePicker = new DatePicker();
    @FXML private Label errorLabel = new Label();
    @FXML private Button mainMenuButton = new Button();
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> customerNameColumn;
    private int appointmentId;
    
    private ObservableList<Customer> customers;
    private ObservableList<String> timesList = FXCollections.observableArrayList("9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM");
    private ObservableList<String> contactList = FXCollections.observableArrayList("Colby Allen", "Madeline Allen", "Miles Allen", "Margaux Allen");
    private ObservableList<String> locationList = FXCollections.observableArrayList("New York", "Boise");
    private ObservableList<String> typeList = FXCollections.observableArrayList("Life Insurance", "Estate Planning", "Stock Consultation", "Investing Questions");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customers = CustomerDao.getAllCustomers();
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTable.setItems(customers);
        
        locationChoiceBox.setItems(locationList);
        timeChoiceBox.setItems(timesList);
        contactChoiceBox.setItems(contactList);
        typeChoiceBox.setItems(typeList);
    }
    
    public void initData(Appointment appointment) {
        locationChoiceBox.getSelectionModel().select(appointment.getLocation());
        contactChoiceBox.getSelectionModel().select(appointment.getContact());
        typeChoiceBox.getSelectionModel().select(appointment.getType());
        timeChoiceBox.getSelectionModel().select(appointment.getStartTimeValue());
        appointmentDatePicker.setValue(appointment.getDateValue());
        
        //HELLO THIS IS MY LAMBDA FUNCTION #1
        IIndexLoop loop = (id) -> {
            for(int i = 0; i < this.customers.size(); i++) {
                if(customers.get(i).getCustomerId() == id) {
                    return i;
                }
            }
            return -1;
        };
        int index = loop.getIndexFromId(appointment.getCustomerId());
        if(index != -1) {
            this.customerTable.getSelectionModel().select(index);
        }
        
        this.appointmentId = appointment.getAppointmentId();
    }
    
    @FXML
    private void modifyAppointment() {
        String result = validateAppointment();
        if(!result.equals("Ok")) {
            errorLabel.setText(result);
        } else {
            //DISPLAY ALERT THAT SAYS SUCCESSFUL
            mainMenuButton.fire();
        }
    }
    
    @FXML
    private String validateAppointment() {
        if(locationChoiceBox.getSelectionModel().isEmpty()) {
            return missingFieldErrorGenerator("Location");
        }
        if(timeChoiceBox.getSelectionModel().isEmpty()) {
            return missingFieldErrorGenerator("Time");
        }
        if(contactChoiceBox.getSelectionModel().isEmpty()) {
            return missingFieldErrorGenerator("Contact");
        }
        if(typeChoiceBox.getSelectionModel().isEmpty()) {
            return missingFieldErrorGenerator("Type");
        }
        if(appointmentDatePicker.getValue() == null) {
            return missingFieldErrorGenerator("Date");
        }
        if(customerTable.getSelectionModel().isEmpty()) {
            return missingFieldErrorGenerator("Customer");
        }
        
        int startHour = TimeFunctions.convertTimeComboToTime(timeChoiceBox.getValue().toString());
        int endHour = startHour + 1;
        
        Appointment tempAppointment = new Appointment(this.appointmentId, customerTable.getSelectionModel().getSelectedItem().getCustomerId(), SessionManager.getSessionUser().getUserId(),
                                                        locationChoiceBox.getValue().toString(), contactChoiceBox.getValue().toString(), typeChoiceBox.getValue().toString(),
                                                        TimeFunctions.createUtcDateTime(startHour, appointmentDatePicker.getValue().toString(), locationChoiceBox.getValue().toString()),
                                                        TimeFunctions.createUtcDateTime(endHour, appointmentDatePicker.getValue().toString(), locationChoiceBox.getValue().toString()));
        
        if(AppointmentDao.verifyDuplicateAppointmentTimeExists(tempAppointment)) {
            return "Error: Appointment already scheduled at that time";
        }
        if(!AppointmentDao.updateAppointment(tempAppointment, SessionManager.getSessionUser())) {
            return "Error: Could not update Appointment";
        }
        
        return "Ok";
    }
    
    @FXML
    private void returnToAptList(ActionEvent ae) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/AllAppointmentView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)ae.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    private String missingFieldErrorGenerator(String field) {
        return "Error: Field '"+ field +"' is required";
    }
}
