package Controllers;

import DAO.AppointmentDao;
import Models.Appointment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author colby
 */
public class CalenderViewController extends BaseController implements Initializable {

    @FXML private RadioButton weekRadioButton;
    @FXML private RadioButton monthRadioButton;
    private ToggleGroup partTypeToggleGroup;
    
    private ObservableList<Appointment> weekList, monthList;
    @FXML private TableView<Appointment> appointmentTable;
    @FXML private TableColumn<Appointment, Integer> appointmentIdColumn, customerIdColumn;
    @FXML private TableColumn<Appointment, String> locationNameColumn, typeNameColumn, startDateColumn, endDateColumn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        monthList = AppointmentDao.getWeeklyMonthlyAppointments(true);
        weekList = AppointmentDao.getWeeklyMonthlyAppointments(false);
        
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        locationNameColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeNameColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("DateValue"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("StartTimeValue"));
        
        this.partTypeToggleGroup = new ToggleGroup();
        this.weekRadioButton.setToggleGroup(partTypeToggleGroup);
        this.weekRadioButton.setSelected(true);
        this.monthRadioButton.setToggleGroup(partTypeToggleGroup);
        
        appointmentTable.setItems(weekList);
    }
    
    @FXML
    private void radioButtonChanged() {
        if(this.partTypeToggleGroup.getSelectedToggle().equals(this.weekRadioButton)) {
            // Display the weeks appointments
            appointmentTable.setItems(weekList);
        }
        else {
            // Display the months appointments
            appointmentTable.setItems(monthList);
        }
    }
}
