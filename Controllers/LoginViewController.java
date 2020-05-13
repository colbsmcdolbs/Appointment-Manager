package Controllers;

import DAO.UserDao;
import Models.User;
import Utils.Logger;
import Utils.SessionManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Utils.LanguageManager;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author colby
 */
public class LoginViewController implements Initializable {
    
    @FXML private TextField UsernameTextField = new TextField();
    @FXML private TextField PasswordTextField = new TextField();
    @FXML private Label WelcomeLabel = new Label();
    @FXML private Label UsernameLabel = new Label();
    @FXML private Label PasswordLabel = new Label();
    @FXML private Label ErrorLabel = new Label();
    @FXML private Button LoginButton = new Button();
    @FXML private Button CancelButton = new Button();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Setup all of the correct languages
        this.WelcomeLabel.setText(LanguageManager.getString("LoginWelcome"));
        this.UsernameLabel.setText(LanguageManager.getString("Username") + ":");
        this.PasswordLabel.setText(LanguageManager.getString("Password") + ":");
        this.LoginButton.setText(LanguageManager.getString("Login"));
        this.CancelButton.setText(LanguageManager.getString("Cancel"));
    }
    
    @FXML
    private void changeSceneDashboard(ActionEvent event) throws IOException {
        if(attemptLogin() == false) {
            return;
        }
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/DashboardView.fxml"));
        Parent addProductParent = loader.load();
        Scene addProductScene = new Scene(addProductParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }

    private boolean attemptLogin() throws IOException {
        String username = this.UsernameTextField.getText();
        String password = this.PasswordTextField.getText();
        this.ErrorLabel.setText("");
        
        if(username.isEmpty() || password.isEmpty())
        {
            this.ErrorLabel.setText(LanguageManager.getString("AllFieldsError"));
            return false;
        }
        
        User attempted = UserDao.findUser(username, password);
        
        if (attempted == null) {
            this.ErrorLabel.setText(LanguageManager.getString("WrongUsernamePassError"));
            Logger.loginFailure(username);
            return false;
        }
        
        SessionManager.setSessionUser(attempted);
        Logger.loginSuccess(username);
        return true;
    }
    
    @FXML
    private void exit() {
        System.exit(0);
    }
            
}
