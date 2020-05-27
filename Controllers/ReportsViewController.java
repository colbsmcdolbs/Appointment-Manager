package Controllers;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author colby
 */
public class ReportsViewController extends BaseController implements Initializable {

    @FXML private ChoiceBox reportsChoiceBox;
    @FXML private Label errorLabel = new Label();
    private ObservableList<String> reportsList = FXCollections.observableArrayList("Number of Appointment Types per Month","Schedule for Each Consultant","Most Popular Appointment Type");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reportsChoiceBox.setItems(reportsList);
    }
    
    @FXML
    private void generateReport(ActionEvent ae) throws IOException{
        String result = validateForm();
        if(!result.equals("Ok")) {
            errorLabel.setText(result);
        } 
        else {
            String reportChoice = reportsChoiceBox.getValue().toString();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/ReportResultView.fxml"));
            Parent addProductParent = loader.load();
            Scene addProductScene = new Scene(addProductParent);
            ReportResultViewController controller = loader.getController();
            switch(reportChoice) {
                case "Number of Appointment Types per Month":
                    controller.GenerateReport1();
                    break;
                case "Schedule for Each Consultant":
                    controller.GenerateReport2();
                    break;
                case "Most Popular Appointment Type":
                    controller.GenerateReport3();
                    break;
            }
            Stage window = (Stage) ((Node)ae.getSource()).getScene().getWindow();
            window.setScene(addProductScene);
            window.show();
        }
    }
    
    private String validateForm() {
        if(reportsChoiceBox.getSelectionModel().isEmpty()) {
            return "Error: No Report Selected";
        }
        
        return "Ok";
    }
}
