package Controllers;

import Utils.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author colby
 */
public class ReportResultViewController implements Initializable {

    @FXML private TextArea resultTextArea = new TextArea();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * This method will generate "Number of appointment types by month"
     */
    public void GenerateReport1() {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String reportQuery = "SELECT type, MONTHNAME(start) as 'Month', COUNT(*) AS 'Total' FROM appointment GROUP BY type, MONTH(start);";
            ResultSet reportResult = connection.executeQuery(reportQuery);
            
            String result = "Number of Appointment Types by Month\n";
            result += "------------------------------------------------------------------------\n";
            while(reportResult.next()) {
                result += "Month: "+ reportResult.getString("Month")+" ||  Type: "+ reportResult.getString("type")+" ||  Count: "+ reportResult.getString("Total")+"\n";
            }
            result += "------------------------------------------------------------------------";
            resultTextArea.setText(result);
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * This method will generate "The schedule for each consultant"
     */
    public void GenerateReport2() {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String reportQuery = "SELECT appointment.contact, appointment.type, customer.customerName, start FROM appointment JOIN customer ON customer.customerId = appointment.customerId GROUP BY appointment.contact, MONTH(start), start;";
            ResultSet reportResult = connection.executeQuery(reportQuery);
            
            String result = "Schedule For Each Consultant\n";
            result += "------------------------------------------------------------------------\n";
            while(reportResult.next()) {
                result += "Name: "+ reportResult.getString("contact") +", Type: "+ reportResult.getString("type") +", Client: "+ reportResult.getString("customerName") +", Start: "+ reportResult.getString("start") +"\n";
            }
            result += "------------------------------------------------------------------------";
            resultTextArea.setText(result);
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * This method will generate the "Most Popular Appointment Type"
     */
    public void GenerateReport3() {
        try {
            Statement connection = DBConnection.getConnection().createStatement();
            String reportQuery = "SELECT type,COUNT(*) as count FROM appointment GROUP BY type ORDER BY count DESC;";
            ResultSet reportResult = connection.executeQuery(reportQuery);
            
            String result = "Total Number of Each Appointment Types\n";
            result += "------------------------------------------------------------------------\n";
            while(reportResult.next()) {
                result += "Type: "+ reportResult.getString("type")+" ||  Count: "+ reportResult.getString("count")+"\n";
            }
            result += "------------------------------------------------------------------------";
            resultTextArea.setText(result);
        }
        catch(SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    
    @FXML
    private void returnToReportGenerator(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ReportsView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
}
