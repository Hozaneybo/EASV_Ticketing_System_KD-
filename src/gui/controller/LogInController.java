package gui.controller;

import be.Admin;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.controller.coordinatorControllers.CreateEventController;
import gui.model.FacadeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    public Button logInButton;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    public FacadeModel facadeModel;

    public static int id;


    public int getCoordinatorId() {
        return id;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            facadeModel = new FacadeModel();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Unable to connect to database");
            alert.setContentText("An error occurred while connecting to the database.");
            alert.showAndWait();
            e.printStackTrace(); // Or log the error
            return; // Exit the method to prevent further errors
        }

    }
       @FXML
       private void handleLogIn(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();



        try {
            Admin admin = facadeModel.getLogInModel().adminLogIn(username, password);
            EventCoordinator coordinator = facadeModel.getLogInModel().coordinatorLogIn(username, password);

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
                //stage.setTitle("Wlecome Coordinator: " + String.valueOf(coordinator.getId()));
                id = coordinator.getId();
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
