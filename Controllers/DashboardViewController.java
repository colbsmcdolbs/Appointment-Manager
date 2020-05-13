package Controllers;

import Utils.SessionManager;
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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author colby
 */
public class DashboardViewController implements Initializable {
    
    @FXML StackPane addCustomerStackPane = new StackPane();
    @FXML Rectangle editCustomerRect = new Rectangle();
    @FXML Rectangle addApptRect = new Rectangle();
    @FXML Rectangle editApptRect = new Rectangle();
    @FXML Rectangle reportsRect = new Rectangle();
    @FXML Rectangle calendarRect = new Rectangle();
    @FXML Button logOutButton = new Button();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void changeSceneAddAppointment(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/AddAppointmentView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneEditAppointment(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/EditAppointmentView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneAddCustomer(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/AddCustomerView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneEditCustomer(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/EditCustomerView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneViewCalendar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/CalenderView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void changeSceneReports(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ReportsView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
    @FXML
    private void logOut(ActionEvent event) throws IOException {
        SessionManager.destroySession();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/LoginView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
}
