package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author colby
 */
public class DashboardViewController implements Initializable {
    
    @FXML Rectangle addCustomerRect = new Rectangle();
    @FXML Rectangle editCustomerRect = new Rectangle();
    @FXML Rectangle addApptRect = new Rectangle();
    @FXML Rectangle editApptRect = new Rectangle();
    @FXML Rectangle reportsRect = new Rectangle();
    @FXML Rectangle calendarRect = new Rectangle();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void changeSceneAddAppointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/AddAppointmentView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneEditAppointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/EditAppointmentView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneAddCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/AddCustomerView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneEditCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/EditCustomerView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneViewCalendar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/CalenderView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneReports(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/ReportsView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
}
