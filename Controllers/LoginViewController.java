package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Utils.LanguageManager;
import javafx.fxml.FXML;

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
    private void attemptLogin() {
        String username = this.UsernameTextField.getText();
        String password = this.PasswordTextField.getText();
        this.ErrorLabel.setText("");
        
        if(username.isEmpty() || password.isEmpty())
        {
            this.ErrorLabel.setText(LanguageManager.getString("AllFieldsError"));
            return;
        }
        
        // 1. Verify User exists in the database
        // 2. If not, return error, edit error label and log as failed login
        // 3. If yes, set sessionuser to this new user, log as success
        // 4. Change scene to the DASHBOARD
    }
    
    @FXML
    private void exit() {
        System.exit(0);
    }
            
}
