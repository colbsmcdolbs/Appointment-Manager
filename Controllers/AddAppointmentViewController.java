package Controllers;

import DAO.AppointmentDao;
import DAO.CustomerDao;
import Models.Appointment;
import Models.Customer;
import Utils.SessionManager;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author colby
 */
public class AddAppointmentViewController extends BaseController implements Initializable {

    @FXML private ChoiceBox locationChoiceBox, timeChoiceBox, contactChoiceBox, typeChoiceBox;
    @FXML private DatePicker appointmentDatePicker = new DatePicker();
    @FXML private Label errorLabel = new Label();
    @FXML private Button mainMenuButton = new Button();
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> customerNameColumn;
    
    private ObservableList<Customer> customers;
    private ObservableList<String> timesList = FXCollections.observableArrayList("9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM");
    private ObservableList<String> contactList = FXCollections.observableArrayList("Colby Allen", "Madeline Allen", "Miles Allen", "Margaux Allen");
    private ObservableList<String> locationList = FXCollections.observableArrayList("New York", "Boise", "Delaware");
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
    
    @FXML
    private void addAppointment() {
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
        
        int startHour = convertTimeComboToTime(timeChoiceBox.getValue().toString());
        int endHour = startHour + 1;
        
        Appointment tempAppointment = new Appointment(-1, customerTable.getSelectionModel().getSelectedItem().getCustomerId(), SessionManager.getSessionUser().getUserId(),
                                                        locationChoiceBox.getValue().toString(), contactChoiceBox.getValue().toString(), typeChoiceBox.getValue().toString(),
                                                        createUtcDateTime(startHour, appointmentDatePicker.getValue().toString()), createUtcDateTime(endHour, appointmentDatePicker.getValue().toString()));
        
        if(AppointmentDao.verifyAppointmentExists(tempAppointment)) {
            return "Error: Appointment already exists";
        }
        if(!AppointmentDao.insertAppointment(tempAppointment, SessionManager.getSessionUser())) {
            return "Error: Could not create Appointment";
        }
        
        return "Ok";
    }
    
    private String missingFieldErrorGenerator(String field) {
        return "Error: Field '"+ field +"' is required";
    }
    
    private int convertTimeComboToTime(String timeValue) {
        switch(timeValue) {
            case "9:00 AM":
                return 9;
            case "10:00 AM":
                return 10;
            case "11:00 AM":
                return 11;
            case "1:00 PM":
                return 13;
            case "2:00 PM":
                return 14;
            case "3:00 PM":
                return 15;
            case "4:00 PM":
                return 16;
            default:
                return -1;
        }
    }
    
    private String createUtcDateTime(int hourValue, String dateValue) {
        String chosenDate = String.format("%s %02d:%s", dateValue, hourValue, "00");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(chosenDate, dateFormatter);
        ZonedDateTime ldtZoned = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneOffset.UTC);
        localDateTime = utcZoned.toLocalDateTime();
        Timestamp timeStamp = Timestamp.valueOf(localDateTime);
        return timeStamp.toString();
    }
}
