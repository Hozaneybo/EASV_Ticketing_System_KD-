package gui.controller;

import be.Admin;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.LogInModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogIn implements Initializable {
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    private LogInModel logInModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInModel = new LogInModel();


    }
       @FXML
       private void handleLogIn(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            Admin admin = logInModel.adminLogIn(username, password);
            EventCoordinator coordinator = logInModel .coordinatorLogIn(username, password);

            if (admin != null) {
                // Login successful, navigate to the next screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/adminGUI/AdminDashboard.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if (coordinator != null) {

                // Login successful, navigate to the next screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/coordinatorGUI/CoordinatorDashboard.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            }
            else {
                // Login failed, show an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Invalid Credentials");
                alert.setContentText("The username or password you entered is incorrect.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            // Handle FXML loading errors
        } catch (SQLServerException e) {
            // Handle database connection errors
        }
    }




}
