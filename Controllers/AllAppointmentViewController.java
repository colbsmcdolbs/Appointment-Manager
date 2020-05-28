package Controllers;

import DAO.AppointmentDao;
import Models.Appointment;
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
public class AllAppointmentViewController extends BaseController implements Initializable {

    @FXML private Button returnToDashboardButton, DeleteButton, ModifyButton;
    @FXML private Label errorLabel = new Label();
    @FXML private TableView<Appointment> appointmentTable;
    @FXML private TableColumn<Appointment, Integer> appointmentIdColumn, customerIdColumn;
    @FXML private TableColumn<Appointment, String> locationNameColumn, typeNameColumn, startDateColumn, endDateColumn;
    private ObservableList<Appointment> appointments;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointments = AppointmentDao.getAllAppointments();
        
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        locationNameColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeNameColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("DateValue"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("SystemLocalStartTimeValue"));
        
        appointmentTable.setItems(appointments);
    }
    
    @FXML
    private void modifyAppointment(ActionEvent ae) throws IOException {
        if(appointmentTable.getSelectionModel().isEmpty()) {
            errorLabel.setText("Error: No Appointment Selected");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/EditAppointmentView.fxml"));
        Parent addPartParent = loader.load();
        Scene addPartScene = new Scene(addPartParent);
        EditAppointmentViewController controller = loader.getController();
        controller.initData(appointmentTable.getSelectionModel().getSelectedItem());
        Stage window = (Stage) ((Node)ae.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    @FXML
    private void deleteAppointment() {
        if(appointmentTable.getSelectionModel().isEmpty()) {
            errorLabel.setText("Error: No Appointment Selected");
        }
        else {
            int appointmentId = appointmentTable.getSelectionModel().getSelectedItem().getAppointmentId();
            AppointmentDao.deleteAppointmentById(appointmentId);
            appointments = AppointmentDao.getAllAppointments();
            appointmentTable.setItems(appointments);
        }
    }
    
}
