package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	 @FXML
	    private Button button;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private TextField username;
	    //hard coded username and password
	    private static final String USERNAME = "alen";
	    private static final String PASSWORD = "alen1234";

	    @FXML
	    //admin login
	    private void userLogin(ActionEvent event) {
	        String enteredUsername = username.getText();
	        String enteredPassword = password.getText();
	        if (isValidCredentials(enteredUsername, enteredPassword)) {
	            try {
	                System.out.println("Login successful!");
	                Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
	                Stage stage = (Stage) username.getScene().getWindow();
	                Scene scene = new Scene(root);
	                stage.setTitle("Bill page");
	                stage.setScene(scene);
	                stage.show();
	            } catch (IOException ex) {
	                // Handle exception 
	                ex.printStackTrace();
	            }
	        } else {
	            System.out.println("Invalid username or password");
	        }
	    }

	    private boolean isValidCredentials(String enteredUsername, String enteredPassword) {
	        return enteredUsername.equals(USERNAME) && enteredPassword.equals(PASSWORD);
	    }

}
